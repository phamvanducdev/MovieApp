package com.ducpv.movie.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ducpv.movie.R
import com.ducpv.movie.base.BaseFragment
import com.ducpv.movie.databinding.FragmentMainBinding
import com.ducpv.movie.ui.bookmark.BookmarkFragment
import com.ducpv.movie.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 26/10/2022.
 */
@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {
    private val homeFragment by lazy { HomeFragment.newInstance() }
    private val bookmarkFragment by lazy { BookmarkFragment.newInstance() }

    private var activeFragment: Fragment? = null

    override val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        super.initialize()
        manuallyChangeTab(R.id.tab_home)
    }

    override fun viewBinding() {
        super.viewBinding()
        binding.bottomNavigationView.itemIconTintList = null
    }

    override fun events() {
        super.events()
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            return@setOnItemSelectedListener onCheckFragmentContains(item.itemId)
        }
    }

    private fun manuallyChangeTab(menu: Int) {
        binding.bottomNavigationView.menu.findItem(menu).isChecked = true
        onCheckFragmentContains(menu)
    }

    private fun onCheckFragmentContains(menu: Int): Boolean {
        return when (menu) {
            R.id.tab_home -> checkFragmentContains(homeFragment)
            R.id.tab_bookmark -> checkFragmentContains(bookmarkFragment)
            else -> false
        }
    }

    private fun checkFragmentContains(fragment: Fragment): Boolean {
        return when {
            fragment == activeFragment -> {
                fragment.onBackToRootFragment()
                false
            }
            childFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) != null -> {
                childFragmentManager.beginTransaction().apply {
                    show(fragment)
                    activeFragment?.let { hide(it) }
                    commit()
                }
                activeFragment = fragment
                fragment.onChangeTabFragment()
                true
            }
            else -> {
                childFragmentManager.beginTransaction().apply {
                    add(R.id.fragmentContainerView, fragment, fragment.javaClass.simpleName)
                    activeFragment?.let { hide(it) }
                    commit()
                }
                activeFragment = fragment
                true
            }
        }
    }
}
