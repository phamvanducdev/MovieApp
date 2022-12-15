package com.ducpv.movie.shared.extension

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by ducpv on 20/11/2022.
 */
suspend fun <T, V> Iterable<T>.asyncAll(
    context: CoroutineContext = EmptyCoroutineContext,
    coroutine: suspend CoroutineScope.(T) -> V
): List<V> = coroutineScope {
    map { async(context) { coroutine(it) } }.awaitAll()
}

suspend fun <T, V> Flow<Iterable<T>>.mapAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    coroutine: suspend CoroutineScope.(T) -> V
): Flow<List<V>> {
    return map { it.asyncAll(context, coroutine) }
}

suspend inline fun <T1, T2, R> combine(
    d1: Deferred<T1>,
    d2: Deferred<T2>,
    transform: (T1, T2) -> R
): R {
    return transform(d1.await(), d2.await())
}

suspend inline fun <T1, T2, T3, R> combine(
    d1: Deferred<T1>,
    d2: Deferred<T2>,
    d3: Deferred<T3>,
    transform: (T1, T2, T3) -> R
): R {
    return transform(d1.await(), d2.await(), d3.await())
}

suspend inline fun <T1, T2, T3, T4, R> combine(
    d1: Deferred<T1>,
    d2: Deferred<T2>,
    d3: Deferred<T3>,
    d4: Deferred<T4>,
    transform: (T1, T2, T3, T4) -> R
): R {
    return transform(d1.await(), d2.await(), d3.await(), d4.await())
}

suspend inline fun <T> Flow<T>.collectOnMain(crossinline invoke: (T) -> Unit) {
    this.collect {
        withContext(Dispatchers.Main) {
            invoke(it)
        }
    }
}
