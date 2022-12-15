package com.ducpv.movie.domain.usecase

import com.ducpv.movie.shared.data.prefs.PreferenceStorage
import com.ducpv.movie.shared.usecase.UseCase
import javax.inject.Inject

/**
 * Created by ducpv on 09/12/2022.
 */
open class OnboardingCompleteActionUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) : UseCase<Boolean, Unit>() {
    override fun execute(parameters: Boolean) {
        preferenceStorage.onboardingCompleted = parameters
    }
}
