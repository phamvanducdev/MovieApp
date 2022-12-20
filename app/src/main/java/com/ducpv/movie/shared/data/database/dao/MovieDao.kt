package com.ducpv.movie.shared.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ducpv.movie.shared.data.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by ducpv on 12/12/2022.
 */
@Dao
interface MovieDao {
    @Query(value = "SELECT * FROM movies")
    fun getMovieEntities(): Flow<List<MovieEntity>>

    @Query(value = """SELECT * FROM movies WHERE id = :movieId""")
    fun getMovieEntity(movieId: String): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieEntities(entities: List<MovieEntity>)

    @Query(value = """DELETE FROM movies WHERE id in (:ids)""")
    suspend fun deleteMovieEntities(ids: List<String>)
}
