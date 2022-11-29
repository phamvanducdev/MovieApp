package com.ducpv.movie.ui.bookmark

import androidx.fragment.app.viewModels
import com.ducpv.movie.base.BaseFragment
import com.ducpv.movie.databinding.FragmentBookmarkBinding
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

    override val viewModel by viewModels<BookmarkViewModel>()

    override fun getViewBinding(): FragmentBookmarkBinding {
        return FragmentBookmarkBinding.inflate(layoutInflater)
    }
}
