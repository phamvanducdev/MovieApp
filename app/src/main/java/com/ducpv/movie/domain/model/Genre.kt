package com.ducpv.movie.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pvduc9773 on 09/01/2023.
 */
data class Genre(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
)
