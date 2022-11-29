package com.ducpv.movie.usecase

/**
 * Created by pvduc9773 on 08/08/2022.
 */
abstract class BaseUseCase<T> {
    abstract suspend fun execute(vararg params: Any): T
}
