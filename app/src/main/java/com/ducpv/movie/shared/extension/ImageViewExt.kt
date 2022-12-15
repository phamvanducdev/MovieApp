package com.ducpv.movie.shared.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ducpv.movie.R
import java.io.File

/**
 * Created by ducpv on 24/10/2022.
 */
fun ImageView.loadImage(file: File?) {
    Glide
        .with(this)
        .load(file)
        .error(R.color.gray)
        .placeholder(R.color.gray)
        .transform()
        .into(this)
}

fun ImageView.loadImage(url: String?) {
    Glide
        .with(this)
        .load(url)
        .error(R.color.gray)
        .placeholder(R.color.gray)
        .transform()
        .into(this)
}
