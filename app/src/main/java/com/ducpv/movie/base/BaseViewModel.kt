package com.ducpv.movie.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import timber.log.Timber

/**
 * Created by pvduc9773 on 25/07/2022.
 */
abstract class BaseViewModel : ViewModel() {
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        Timber.e("coroutineException: $e")
    }

    protected fun onLaunchCoroutine(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        executeBlock: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(coroutineExceptionHandler + dispatcher) {
            executeBlock()
        }
    }
}
