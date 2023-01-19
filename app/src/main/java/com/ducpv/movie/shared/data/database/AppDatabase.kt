package com.ducpv.movie.shared.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ducpv.movie.shared.data.database.dao.GenreDao
import com.ducpv.movie.shared.data.database.dao.MovieDao
import com.ducpv.movie.shared.data.database.entity.GenreEntity
import com.ducpv.movie.shared.data.database.entity.MovieEntity

/**
 * Created by ducpv on 09/12/2022.
 */
@Database(
    entities = [
        MovieEntity::class,
        GenreEntity::class
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    AppConverters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao

    companion object {
        private const val databaseName = "movie-db"

        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
        }
    }
}
