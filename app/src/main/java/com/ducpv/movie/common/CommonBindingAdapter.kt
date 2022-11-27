package com.ducpv.movie.common

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.ducpv.movie.extension.loadImage
import java.io.File

/**
 * Created by pvduc9773 on 24/10/2022.
 */
@BindingAdapter("appBindingString")
fun TextView.bindingString(string: String?) {
    if (string == null) return
    this.text = string
}

@BindingAdapter("appBindingStringRes")
fun TextView.bindingStringRes(@StringRes stringRes: Int?) {
    if (stringRes == null || stringRes == -1) return
    this.setText(stringRes)
}

@BindingAdapter("appBindingImageUrl")
fun ImageView.bindingImageUrl(url: String?) {
    this.loadImage(url)
}

@BindingAdapter("appBindingImageFile")
fun ImageView.bindingImageFile(file: File?) {
    this.loadImage(file)
}
