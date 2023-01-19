package com.ducpv.movie.ui.home

import androidx.lifecycle.asLiveData
import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.usecase.BookmarkMovieUseCase
import com.ducpv.movie.domain.usecase.GetMoviesPopularUseCase
import com.ducpv.movie.domain.usecase.GetMoviesShowingUseCase
import com.ducpv.movie.domain.usecase.UnBookmarkMovieUseCase
import com.ducpv.movie.shared.base.BaseViewModel
import com.ducpv.movie.shared.network.NetworkMonitor
import com.ducpv.movie.shared.result.Result
import com.ducpv.movie.shared.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Created by ducpv on 26/07/2022.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor,
    private val getMoviesShowingUseCase: GetMoviesShowingUseCase,
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase,
    private val bookmarkMovieUseCase: BookmarkMovieUseCase,
    private val unBookmarkMovieUseCase: UnBookmarkMovieUseCase
) : BaseViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asLiveData()

    init {
        onLaunchCoroutine {
            networkMonitor.isOnline
                .distinctUntilChanged()
                .collect { isOnline ->
                    if (isOnline) {
                        fetchData()
                    } else {
                        if (_uiState.value !is HomeUiState.Success) {
                            _uiState.value = HomeUiState.Error
                        }
                    }
                }
        }
    }

    private suspend fun fetchData() {
        combine(
            getMoviesShowingUseCase(),
            getMoviesPopularUseCase(),
            ::Pair
        )
            .asResult()
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        val (showings, populars) = result.data
                        _uiState.value = HomeUiState.Success(showings, populars)
                    }
                    is Result.Loading -> {
                        _uiState.value = HomeUiState.Loading
                    }
                    is Result.Error -> {
                        _uiState.value = HomeUiState.Error
                    }
                }
            }
    }

    fun onClickBookmarkMovie(movie: Movie) {
        onLaunchCoroutine {
            if (movie.isBookmarked) {
                bookmarkMovieUseCase(movie)
            } else {
                unBookmarkMovieUseCase(movie.id)
            }
        }
    }
}

sealed interface HomeUiState {
    data class Success(
        val showings: List<Movie> = emptyList(),
        val populars: List<Movie> = emptyList()
    ) : HomeUiState

    object Loading : HomeUiState
    object Error : HomeUiState

    val uiItems: List<ItemHomeUi>
        get() {
            val items = mutableListOf<ItemHomeUi>()
            when (this) {
                is Success -> {
                    if (showings.isNotEmpty()) {
                        items.add(ItemHomeUi.ItemShowings(showings))
                    }
                    if (populars.isNotEmpty()) {
                        items.add(ItemHomeUi.ItemHeaderPopulars())
                        items.addAll(
                            populars.map { ItemHomeUi.ItemMoviePopular(it) }
                        )
                    }
                }
                is Loading -> {
                    items.add(ItemHomeUi.ItemLoading())
                }
                is Error -> Unit
            }
            return items
        }

    val isEmpty: Boolean get() = uiItems.isEmpty() || this is Error
}
