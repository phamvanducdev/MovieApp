package com.ducpv.movie.ui.bookmark

import androidx.fragment.app.viewModels
import com.ducpv.movie.databinding.FragmentBookmarkBinding
import com.ducpv.movie.shared.base.BaseFragment
import com.ducpv.movie.shared.extension.setVisible
import com.ducpv.movie.shared.extension.singleObserve
import com.ducpv.movie.ui.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 28/11/2022.
 */
@AndroidEntryPoint
class BookmarkFragment : BaseFragment<BookmarkViewModel, FragmentBookmarkBinding>() {
    companion object {
        fun newInstance(): BookmarkFragment {
            return BookmarkFragment()
        }
    }

    private val bookmarkAdapter: BookmarkAdapter by lazy {
        BookmarkAdapter {
            viewModel.onClickMovieDetail(it)
        }
    }

    override val viewModel by viewModels<BookmarkViewModel>()

    override fun getViewBinding(): FragmentBookmarkBinding {
        return FragmentBookmarkBinding.inflate(layoutInflater)
    }

    override fun viewBinding() {
        super.viewBinding()
        binding.rvBookmark.adapter = bookmarkAdapter
    }

    override fun observeViewModel() {
        super.observeViewModel()
        singleObserve(viewModel.uiState) {
            bookmarkAdapter.submitList(it)
            binding.tvEmpty.setVisible(it.isEmpty())
        }
        singleObserve(viewModel.navigationMovieDetail) {
            navigateTo(MainFragmentDirections.actionMainFragmentToDetailFragment(it))
        }
    }
}
