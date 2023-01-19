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
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Long,
    @SerializedName("genre_ids") val genreIds: List<String> = emptyList(),
    @SerializedName("genres") val genres: List<Genre> = emptyList()
) {
    var isBookmarked: Boolean = false
    var casts: List<Cast> = emptyList()
}
