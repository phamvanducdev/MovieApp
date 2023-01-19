package com.ducpv.movie.ui.onboarding

import android.content.Intent
import androidx.activity.viewModels
import com.ducpv.movie.databinding.ActivityOnboardingBinding
import com.ducpv.movie.shared.base.BaseActivity
import com.ducpv.movie.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by ducpv on 09/12/2022.
 */
@AndroidEntryPoint
class OnboardingActivity : BaseActivity<OnboardingViewModel, ActivityOnboardingBinding>() {
    override val viewModel: OnboardingViewModel by viewModels()

    override fun getViewBinding() = ActivityOnboardingBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.navigateToMainActivity.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
