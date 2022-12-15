package com.ducpv.movie.shared.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Created by ducpv on 05/12/2022.
 */
fun <T> LifecycleOwner.singleObserve(liveData: LiveData<T>, action: (t: T) -> Unit) {
    if (!liveData.hasObservers()) {
        liveData.observe(this) { action(it) }
    }
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this) { action(it) }
}
