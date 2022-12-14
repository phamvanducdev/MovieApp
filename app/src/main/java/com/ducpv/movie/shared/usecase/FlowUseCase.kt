package com.ducpv.movie.shared.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by pvduc9773 on 09/12/2022.
 */
abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<R> {
        return execute(parameters).flowOn(coroutineDispatcher)
    }

    @Throws(RuntimeException::class)
    abstract fun execute(parameters: P): Flow<R>
}
