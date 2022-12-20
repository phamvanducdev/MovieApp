package com.ducpv.movie.domain.usecase

import com.ducpv.movie.shared.data.prefs.PreferenceStorage
import javax.inject.Inject

/**
 * Created by ducpv on 09/12/2022.
 */
open class OnboardingCompleteActionUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) {
    operator fun invoke(isCompleted: Boolean) {
        preferenceStorage.onboardingCompleted = isCompleted
    }
}
