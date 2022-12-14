package com.ducpv.movie.shared.extension

import android.app.Activity
import android.view.WindowManager

/**
 * Created by ducpv on 24/11/2022.
 */
fun Activity.fitsSystemWindows(enabled: Boolean) {
    if (enabled) {
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    } else {
        window?.clearFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}
