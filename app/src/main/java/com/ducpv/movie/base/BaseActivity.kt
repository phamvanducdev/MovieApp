package com.ducpv.movie.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.ViewDataBinding

/**
 * Created by pvduc9773 on 25/07/2022.
 */
abstract class BaseActivity<T : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    protected abstract val viewModel: T
    protected lateinit var binding: B
    abstract fun getViewBinding(): B

    open fun initialize() {}
    open fun observeViewModel() {}
    open fun viewBinding() {}
    open fun events() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = getViewBinding()
        binding.lifecycleOwner = this
        setContentView(binding.root)
        initialize()
        viewBinding()
        observeViewModel()
        events()
    }
}
