package com.ducpv.movie.shared.extension

import android.content.res.Resources

/**
 * Created by pvduc9773 on 30/11/2022.
 */
val Int.dp: Int get() = (Resources.getSystem().displayMetrics.density * this).toInt()
