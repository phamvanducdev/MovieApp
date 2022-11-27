package com.ducpv.movie.ui.detail

import androidx.fragment.app.viewModels
import com.ducpv.movie.base.BaseFragment
import com.ducpv.movie.databinding.FragmentDetailBinding
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
}
