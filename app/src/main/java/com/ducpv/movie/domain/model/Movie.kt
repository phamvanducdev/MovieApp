package com.ducpv.movie.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ducpv on 28/11/2022.
 */
data class Movie(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Long,
) {
    var isBookmarked: Boolean = false
}
