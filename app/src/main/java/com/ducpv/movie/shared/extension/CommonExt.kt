package com.ducpv.movie.shared.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by ducpv on 08/08/2022.
 */
inline fun <reified T> Gson.fromJson(json: String?): T? = fromJson(json, object : TypeToken<T>() {}.type)
