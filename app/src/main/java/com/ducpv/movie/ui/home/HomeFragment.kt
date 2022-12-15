package com.ducpv.movie.ui.home

import androidx.fragment.app.viewModels
import com.ducpv.movie.databinding.FragmentHomeBinding
import com.ducpv.movie.shared.base.BaseFragment
import com.ducpv.movie.shared.extension.setVisible
import com.ducpv.movie.shared.extension.singleObserve
import com.ducpv.movie.ui.main.MainFragmentDirections
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
                viewModel.onClickMovieDetail(it)
            },
            onViewMoreNowShowingClickListener = {},
            onViewMorePopularClickListener = {}
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
        singleObserve(viewModel.uiState) {
            adapter.submitList(it.uiItems)
            binding.tvEmpty.setVisible(it.emptyView)
        }
        singleObserve(viewModel.navigationMovieDetail) {
            navigateTo(MainFragmentDirections.actionMainFragmentToDetailFragment(it))
        }
    }
}
