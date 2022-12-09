package com.ducpv.movie.ui.bookmark

import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.ducpv.movie.shared.base.BaseFragment
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

    override fun events() {
        super.events()
        binding.root.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("movie://detail/724495".toUri())
                .build()
            findNavController().navigate(request)
        }
    }
}
