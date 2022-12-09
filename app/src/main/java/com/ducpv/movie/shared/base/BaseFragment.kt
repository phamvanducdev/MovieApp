package com.ducpv.movie.shared.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ducpv.movie.shared.utils.defaultNavOptions

/**
 * Created by pvduc9773 on 25/07/2022.
 */
abstract class BaseFragment<T : BaseViewModel, B : ViewDataBinding> : Fragment() {

    protected abstract val viewModel: T
    protected lateinit var binding: B

    abstract fun getViewBinding(): B

    open fun initialize() {}
    open fun observeViewModel() {}
    open fun viewBinding() {}
    open fun events() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
        observeViewModel()
        viewBinding()
        events()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.lifecycleOwner = null
    }

    fun navigateTo(directions: NavDirections, navOptions: NavOptions? = defaultNavOptions) {
        findNavController().navigate(directions, navOptions)
    }
}
