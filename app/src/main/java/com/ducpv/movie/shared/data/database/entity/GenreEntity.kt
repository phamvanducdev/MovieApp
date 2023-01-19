package com.ducpv.movie.shared.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ducpv.movie.domain.model.Genre

/**
 * Created by pvduc9773 on 09/01/2023.
 */
@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey
    val id: String,
    val name: String
)

fun Genre.asEntity() = GenreEntity(
    id = id,
    name = name
)

fun GenreEntity.asExternalModel() = Genre(
    id = id,
    name = name
)
