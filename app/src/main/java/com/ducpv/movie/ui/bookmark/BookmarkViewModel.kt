package com.ducpv.movie.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.usecase.GetBookmarkMoviesUseCase
import com.ducpv.movie.domain.usecase.UnBookmarkMovieUseCase
import com.ducpv.movie.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

/**
 * Created by ducpv on 26/07/2022.
 */
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    getBookmarkMoviesUseCase: GetBookmarkMoviesUseCase,
    private val unBookmarkMovieUseCase: UnBookmarkMovieUseCase
) : BaseViewModel() {
    val uiState = getBookmarkMoviesUseCase(Unit)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
        .asLiveData()

    private val _navigationMovieDetail = MutableLiveData<String>()
    val navigationMovieDetail: LiveData<String> = _navigationMovieDetail

    fun onClickMovieDetail(movie: Movie) {
        onLaunchCoroutine {
            unBookmarkMovieUseCase(listOf(movie).map(Movie::id))
            _navigationMovieDetail.postValue(movie.id)
        }
    }
}
