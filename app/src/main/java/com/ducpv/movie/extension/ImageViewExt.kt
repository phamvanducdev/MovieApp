package com.ducpv.movie.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ducpv.movie.R
import java.io.File

/**
 * Created by pvduc9773 on 24/10/2022.
 */
fun ImageView.loadImage(file: File?) {
    Glide
        .with(this)
        .load(file)
        .error(R.color.object_warning)
        .placeholder(R.color.object_light_gray)
        .transform()
        .into(this)
}

fun ImageView.loadImage(url: String?) {
    Glide
        .with(this)
        .load(url)
        .error(R.color.object_warning)
        .placeholder(R.color.object_light_gray)
        .transform()
        .into(this)
}
