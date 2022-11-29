package com.ducpv.movie.ui.home

import androidx.fragment.app.viewModels
import com.ducpv.movie.base.BaseFragment
import com.ducpv.movie.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 26/10/2022.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val adapter: HomeAdapter by lazy {
        HomeAdapter(
            onItemMovieClickListener = {
                // TODO
            }
        )
    }

    override val viewModel by viewModels<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun viewBinding() {
        super.viewBinding()
        binding.rvHome.adapter = this.adapter
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.uiState.observe(viewLifecycleOwner) {
            adapter.submitList(it.uiItems)
        }
    }
}
