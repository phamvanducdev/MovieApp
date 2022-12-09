package com.ducpv.movie.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import com.ducpv.movie.shared.base.BaseViewModel
import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by pvduc9773 on 26/07/2022.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel() {
    companion object {
        const val MOVIE_ID_SAVED_STATE_KEY = "movieId"
    }

    private val movieId: String = savedStateHandle.get<String>(MOVIE_ID_SAVED_STATE_KEY)!!

    private val _uiState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Loading)
    val uiState = _uiState.asLiveData()

    init {
        onLaunchCoroutine {
            val movie = getMovieDetailUseCase(movieId)
            _uiState.value = MovieDetailUiState.Success(movie)
        }
    }

    sealed interface MovieDetailUiState {
        object Loading : MovieDetailUiState
        data class Success(val movie: Movie) : MovieDetailUiState
    }
}
