package com.ducpv.movie.ui.home

import androidx.lifecycle.asLiveData
import com.ducpv.movie.base.BaseViewModel
import com.ducpv.movie.model.Movie
import com.ducpv.movie.usecase.GetMoviesNowPlayingUseCase
import com.ducpv.movie.usecase.GetMoviesPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * Created by pvduc9773 on 26/07/2022.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesNowPlayingUseCase: GetMoviesNowPlayingUseCase,
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asLiveData()

    init {
        onLaunchCoroutine {
            try {
                val moviesNowShowing = getMoviesNowPlayingUseCase.execute()
                val moviesPopular = getMoviesPopularUseCase.execute()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        moviesNowShowing = moviesNowShowing,
                        moviesPopular = moviesPopular
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = true,
    val moviesNowShowing: List<Movie> = emptyList(),
    val moviesPopular: List<Movie> = emptyList()
) {
    val uiItems: List<ItemHomeUi>
        get() {
            val items = mutableListOf<ItemHomeUi>()
            if (moviesNowShowing.isNotEmpty()) {
                items.add(ItemHomeUi.ItemNowShowings(moviesNowShowing))
            }
            if (moviesPopular.isNotEmpty()) {
                items.add(ItemHomeUi.ItemHeaderPopulars())
                items.addAll(
                    moviesPopular.map {
                        ItemHomeUi.ItemMoviePopular(it)
                    }
                )
            }
            if (isLoading) {
                items.add(ItemHomeUi.ItemLoading())
            }
            return items
        }
}
