package com.ducpv.movie.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pvduc9773 on 12/01/2023.
 */
data class Credits(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val casts: List<Cast>
)
