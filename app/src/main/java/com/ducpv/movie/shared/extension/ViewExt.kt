package com.ducpv.movie.shared.extension

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by pvduc9773 on 26/07/2022.
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.setVisible(isVisible: Boolean) {
    if (isVisible) show() else hide()
}

fun View.isRtl() = layoutDirection == View.LAYOUT_DIRECTION_RTL

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
}
