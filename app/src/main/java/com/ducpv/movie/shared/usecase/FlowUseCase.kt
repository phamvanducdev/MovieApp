package com.ducpv.movie.shared.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

/**
 * Created by pvduc9773 on 09/12/2022.
 */
abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    operator fun invoke(parameters: P): Flow<R> {
        return execute(parameters)
            .catch { e ->
                Timber.e(e)
                throw e
            }
            .flowOn(coroutineDispatcher)
    }

    abstract fun execute(parameters: P): Flow<R>
}
