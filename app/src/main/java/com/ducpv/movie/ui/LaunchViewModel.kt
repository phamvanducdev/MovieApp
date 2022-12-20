package com.ducpv.movie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ducpv.movie.domain.usecase.OnboardingCompletedUseCase
import com.ducpv.movie.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay

/**
 * Created by ducpv on 09/12/2022.
 */
@HiltViewModel
class LaunchViewModel @Inject constructor(
    onboardingCompletedUseCase: OnboardingCompletedUseCase
) : BaseViewModel() {
    private val _onLaunchDestination = MutableLiveData<LaunchDestination>()
    val onLaunchDestination: LiveData<LaunchDestination> = _onLaunchDestination

    init {
        onLaunchCoroutine {
            delay(3_000L)
            if (onboardingCompletedUseCase()) {
                _onLaunchDestination.postValue(LaunchDestination.MAIN_ACTIVITY)
            } else {
                _onLaunchDestination.postValue(LaunchDestination.ONBOARDING)
            }
        }
    }
}

enum class LaunchDestination {
    ONBOARDING,
    MAIN_ACTIVITY
}
