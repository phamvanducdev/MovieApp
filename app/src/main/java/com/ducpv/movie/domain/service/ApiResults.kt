package com.ducpv.movie.domain.service

/**
 * Created by ducpv on 15/11/2022.
 */
class ApiResults<T>(
    val results: List<T>,
    val data: T
)
