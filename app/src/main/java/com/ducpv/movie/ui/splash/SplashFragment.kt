package com.ducpv.movie.ui.splash

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ducpv.movie.base.BaseFragment
import com.ducpv.movie.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 27/11/2022.
 */
@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {
    override val viewModel: SplashViewModel by viewModels()

    override fun getViewBinding(): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        super.initialize()
        viewModel.initApp()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.uiState.observe(viewLifecycleOwner) {
            binding.tvLoading.isVisible = it is SplashUiState.Loading
            if (it is SplashUiState.Finish) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
            }
        }
    }
}
