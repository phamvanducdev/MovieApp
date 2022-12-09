package com.ducpv.movie.shared.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ducpv.movie.databinding.ViewLoadingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by pvduc9773 on 30/11/2022.
 */
class LoadingTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewLoadingBinding by lazy {
        ViewLoadingBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        CoroutineScope(Dispatchers.Main).launch {
            addDotToText()
        }
    }

    private suspend fun addDotToText() {
        var count = 0
        while (true) {
            count %= 4
            binding.tvDot.text = ".".repeat(count)
            delay(600)
            count++
        }
    }
}
