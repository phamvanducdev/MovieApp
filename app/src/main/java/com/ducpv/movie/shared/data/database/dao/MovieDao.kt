package com.ducpv.movie.shared.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.ducpv.movie.shared.data.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by pvduc9773 on 12/12/2022.
 */
@Dao
interface MovieDao {
    @Query(value = "SELECT * FROM movies")
    fun getMovieEntities(): Flow<List<MovieEntity>>

    @Update
    suspend fun updateMovies(entities: List<MovieEntity>)

    @Upsert
    suspend fun upsertMovies(entities: List<MovieEntity>)
}
