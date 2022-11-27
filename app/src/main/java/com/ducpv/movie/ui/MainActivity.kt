package com.ducpv.movie.ui

import androidx.activity.viewModels
import com.ducpv.movie.base.BaseActivity
import com.ducpv.movie.databinding.ActivityMainBinding
import com.ducpv.movie.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}
