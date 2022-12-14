package com.ducpv.movie.ui

import androidx.activity.viewModels
import com.ducpv.movie.databinding.ActivityMainBinding
import com.ducpv.movie.shared.base.BaseActivity
import com.ducpv.movie.shared.extension.singleObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        super.observeViewModel()
        singleObserve(viewModel.networkState) { isOnline ->
            if (!isOnline) {
                showSnackbar("⚠️ You aren’t connected to the internet")
            }
        }
    }
}
