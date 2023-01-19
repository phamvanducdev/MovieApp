package com.ducpv.movie.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pvduc9773 on 12/01/2023.
 */
data class Cast(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("profile_path") val profilePath: String,
    @SerializedName("character") val character: String,
)
