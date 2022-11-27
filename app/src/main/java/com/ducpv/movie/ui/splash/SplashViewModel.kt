package com.ducpv.movie.ui.splash

import androidx.lifecycle.asLiveData
import com.ducpv.movie.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by pvduc9773 on 26/07/2022.
 */
@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState = _uiState.asLiveData()

    fun initApp() {
        onLaunchCoroutine {
            delay(3_000L)
            _uiState.value = SplashUiState.Finish
        }
    }
}

sealed class SplashUiState {
    object Loading : SplashUiState()
    object Finish : SplashUiState()
}
