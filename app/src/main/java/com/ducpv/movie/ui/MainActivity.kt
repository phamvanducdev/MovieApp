package com.ducpv.movie.ui

import androidx.activity.viewModels
import com.ducpv.movie.shared.base.BaseActivity
import com.ducpv.movie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}
