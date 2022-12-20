package com.ducpv.movie.shared.extension

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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

fun <T1, T2, T3, T4, T5, T6, R> combine(
    flow: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    transform: suspend (T1, T2, T3, T4, T5, T6) -> R
): Flow<R> = combine(
    combine(flow, flow2, flow3, ::Triple),
    combine(flow4, flow5, flow6, ::Triple)
) { t1, t2 ->
    transform(
        t1.first,
        t1.second,
        t1.third,
        t2.first,
        t2.second,
        t2.third
    )
}
