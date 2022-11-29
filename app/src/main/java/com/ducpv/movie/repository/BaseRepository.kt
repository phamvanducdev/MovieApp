package com.ducpv.movie.repository

import com.ducpv.movie.BuildConfig

/**
 * Created by pvduc9773 on 29/11/2022.
 */
open class BaseRepository {
    val apiKey: String get() = BuildConfig.API_KEY
}
