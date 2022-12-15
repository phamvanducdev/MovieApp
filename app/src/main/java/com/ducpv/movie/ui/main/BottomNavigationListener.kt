package com.ducpv.movie.ui.main

import androidx.fragment.app.Fragment

/**
 * Created by ducpv on 28/11/2022.
 */
interface BottomNavigationListener {
    fun onBackToRoot()
    fun onChangeTab()
}

fun Fragment.onBackToRootFragment() {
    when (this) {
        is BottomNavigationListener -> {
            this.onBackToRoot()
        }
    }
}

fun Fragment.onChangeTabFragment() {
    when (this) {
        is BottomNavigationListener -> {
            this.onChangeTab()
        }
    }
}
