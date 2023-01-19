package com.ducpv.movie.ui.bookmark

import androidx.fragment.app.viewModels
import com.ducpv.movie.databinding.FragmentBookmarkBinding
import com.ducpv.movie.shared.base.BaseFragment
import com.ducpv.movie.shared.extension.dp
import com.ducpv.movie.shared.extension.setVisible
import com.ducpv.movie.shared.extension.singleObserve
import com.ducpv.movie.shared.widget.GridSpaceDecoration
import com.ducpv.movie.ui.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by ducpv on 28/11/2022.
 */
@AndroidEntryPoint
class BookmarkFragment : BaseFragment<BookmarkViewModel, FragmentBookmarkBinding>() {
    companion object {
        fun newInstance(): BookmarkFragment {
            return BookmarkFragment()
        }
    }

    private val adapter: BookmarkAdapter by lazy {
        BookmarkAdapter {
            viewModel.onClickMovieDetail(it)
        }
    }

    override val viewModel by viewModels<BookmarkViewModel>()

    override fun getViewBinding(): FragmentBookmarkBinding {
        return FragmentBookmarkBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        super.initialize()
        binding.rvBookmark.adapter = adapter
        binding.rvBookmark.addItemDecoration(
            GridSpaceDecoration(
                spanCount = 2,
                spacingHorizontal = 8.dp,
                spacingVertical = 8.dp,
            )
        )
    }

    override fun observeViewModel() {
        super.observeViewModel()
        singleObserve(viewModel.uiState) {
            adapter.submitList(it)
            binding.tvEmpty.setVisible(it.isEmpty())
        }
        singleObserve(viewModel.navigationMovieDetail) {
            navigateTo(MainFragmentDirections.actionMainFragmentToDetailActivity(it))
        }
    }
}
