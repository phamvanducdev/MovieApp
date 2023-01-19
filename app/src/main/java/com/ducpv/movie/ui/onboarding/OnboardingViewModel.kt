package com.ducpv.movie.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ducpv.movie.domain.usecase.OnboardingCompleteActionUseCase
import com.ducpv.movie.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay

/**
 * Created by ducpv on 09/12/2022.
 */
@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingCompleteActionUseCase: OnboardingCompleteActionUseCase
) : BaseViewModel() {
    private val _navigateToMainActivity = MutableLiveData<Unit>()
    val navigateToMainActivity: LiveData<Unit> = _navigateToMainActivity

    init {
        onLaunchCoroutine {
            delay(3_000L)
            onboardingCompleteActionUseCase(true)
            _navigateToMainActivity.postValue(Unit)
        }
    }
}
