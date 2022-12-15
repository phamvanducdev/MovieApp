package com.ducpv.movie.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ducpv.movie.domain.usecase.GetMovieDetailUseCase
import com.ducpv.movie.shared.base.BaseViewModel
import com.ducpv.movie.shared.result.Result
import com.ducpv.movie.shared.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

/**
 * Created by ducpv on 26/07/2022.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getMovieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel() {
    companion object {
        const val MOVIE_ID_SAVED_STATE_KEY = "movieId"
    }

    private val movieId: String = savedStateHandle.get<String>(MOVIE_ID_SAVED_STATE_KEY)!!

    private val _movieDetailState = getMovieDetailUseCase(movieId)
    val movieDetailState = _movieDetailState
        .asResult()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Result.Loading
        )
        .asLiveData()
}
