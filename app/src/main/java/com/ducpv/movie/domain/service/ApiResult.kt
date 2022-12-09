package com.ducpv.movie.domain.service

/**
 * Created by pvduc9773 on 15/11/2022.
 */
class ApiResult<T>(
    val code: Int,
    val body: T?,
    val message: String?
)

class ApiResults<T>(
    val page: Int,
    val results: List<T>
)
