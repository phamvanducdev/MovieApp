package com.ducpv.movie.shared.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ducpv.movie.shared.data.database.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by ducpv on 12/12/2022.
 */
@Dao
interface GenreDao {
    @Query(value = "SELECT * FROM genres")
    fun getGenreEntities(): Flow<List<GenreEntity>>

    @Query(value = "SELECT * FROM genres WHERE id = :genreId")
    fun getGenreEntity(genreId: String): Flow<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenreEntities(entities: List<GenreEntity>)

    @Query(value = "DELETE FROM genres WHERE id in (:ids)")
    suspend fun deleteGenreEntities(ids: List<String>)
}
