package com.ducpv.movie.ui.home

import androidx.lifecycle.asLiveData
import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.usecase.GetMoviesNowPlayingUseCase
import com.ducpv.movie.domain.usecase.GetMoviesPopularUseCase
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
 * Created by pvduc9773 on 26/07/2022.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor,
    private val getMoviesNowPlayingUseCase: GetMoviesNowPlayingUseCase,
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase
) : BaseViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asLiveData()

    init {
        onLaunchCoroutine {
            networkMonitor.isOnline
                .distinctUntilChanged()
                .collect {
                    fetchData()
                }
        }
    }

    private suspend fun fetchData() {
        combine(
            getMoviesNowPlayingUseCase(Unit),
            getMoviesPopularUseCase(Unit),
            ::Pair
        )
            .asResult()
            .collect { result ->
                when (result) {
                    is Result.Success -> {
                        val (nowShowings, populars) = result.data
                        _uiState.value = HomeUiState.Success(nowShowings, populars)
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
}

sealed interface HomeUiState {
    data class Success(
        val nowShowings: List<Movie> = emptyList(),
        val populars: List<Movie> = emptyList()
    ) : HomeUiState

    object Loading : HomeUiState
    object Error : HomeUiState

    val uiItems: List<ItemHomeUi>
        get() {
            val items = mutableListOf<ItemHomeUi>()
            when (this) {
                is Success -> {
                    if (nowShowings.isNotEmpty()) {
                        items.add(ItemHomeUi.ItemNowShowings(nowShowings))
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
}
