package com.ducpv.movie.ui.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ducpv.movie.shared.base.BaseFragment
import com.ducpv.movie.databinding.FragmentDetailBinding
import com.ducpv.movie.shared.extension.hide
import com.ducpv.movie.shared.extension.show
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 26/10/2022.
 */
@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {
    override val viewModel by viewModels<DetailViewModel>()

    override fun getViewBinding(): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is DetailViewModel.MovieDetailUiState.Loading -> {
                    binding.viewLoading.show()
                }
                is DetailViewModel.MovieDetailUiState.Success -> {
                    binding.viewLoading.hide()
                    binding.toolbar.title = it.movie.title
                }
            }
        }
    }

    override fun events() {
        super.events()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}
