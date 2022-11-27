package com.ducpv.movie.ui.main

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ducpv.movie.base.BaseFragment
import com.ducpv.movie.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 26/10/2022.
 */
@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {
    override val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun viewBinding() {
        super.viewBinding()
        binding.root.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment())
        }
    }
}
