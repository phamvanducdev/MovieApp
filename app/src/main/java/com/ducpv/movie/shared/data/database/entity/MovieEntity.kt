package com.ducpv.movie.shared.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ducpv.movie.domain.model.Movie

/**
 * Created by pvduc9773 on 12/12/2022.
 */
@Entity(
    tableName = "movies",
)
data class MovieEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Long,
)

fun MovieEntity.asExternalModel() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

