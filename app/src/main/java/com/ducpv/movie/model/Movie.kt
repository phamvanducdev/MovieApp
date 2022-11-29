package com.ducpv.movie.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pvduc9773 on 28/11/2022.
 */
data class Movie(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Long,
) {
    val poster: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
}
