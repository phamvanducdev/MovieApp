package com.ducpv.movie.ui

import android.content.Intent
import androidx.activity.viewModels
import com.ducpv.movie.shared.base.BaseActivity
import com.ducpv.movie.databinding.ActivityLauncherBinding
import com.ducpv.movie.ui.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 09/12/2022.
 */
@AndroidEntryPoint
class LauncherActivity : BaseActivity<LaunchViewModel, ActivityLauncherBinding>() {
    override val viewModel: LaunchViewModel by viewModels()

    override fun getViewBinding() = ActivityLauncherBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.onLaunchDestination.observe(this) { destination ->
            when (destination) {
                LaunchDestination.ONBOARDING -> {
                    startActivity(Intent(this, OnboardingActivity::class.java))
                }
                else -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
            finish()
        }
    }
}
