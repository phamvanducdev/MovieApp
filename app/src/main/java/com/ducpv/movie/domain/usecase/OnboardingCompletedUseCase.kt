package com.ducpv.movie.domain.usecase

import com.ducpv.movie.shared.data.prefs.PreferenceStorage
import com.ducpv.movie.shared.usecase.UseCase
import javax.inject.Inject

/**
 * Created by ducpv on 09/12/2022.
 */
class OnboardingCompletedUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage,
) : UseCase<Unit, Boolean>() {
    override fun execute(parameters: Unit): Boolean {
        return preferenceStorage.onboardingCompleted
    }
}
