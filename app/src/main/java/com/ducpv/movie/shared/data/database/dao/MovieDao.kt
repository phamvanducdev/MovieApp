package com.ducpv.movie.shared.data.database.dao

import androidx.room.*
import com.ducpv.movie.shared.data.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by ducpv on 12/12/2022.
 */
@Dao
interface MovieDao {
    @Query(value = "SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Update
    suspend fun updateMovies(entities: List<MovieEntity>)

    @Upsert
    suspend fun upsertMovies(entities: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(entities: List<MovieEntity>)

    @Query(value = """DELETE FROM movies WHERE id in (:ids)""")
    suspend fun deleteMovies(ids: List<String>)
}
