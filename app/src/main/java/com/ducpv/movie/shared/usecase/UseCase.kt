package com.ducpv.movie.shared.usecase

import timber.log.Timber

/**
 * Created by ducpv on 08/08/2022.
 */
abstract class UseCase<in P, R> {

    operator fun invoke(parameters: P): R {
        return try {
            execute(parameters)
        } catch (e: Exception) {
            Timber.e(e)
            throw e
        }
    }

    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P): R
}
