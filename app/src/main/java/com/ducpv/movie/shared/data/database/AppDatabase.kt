package com.ducpv.movie.shared.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ducpv.movie.shared.data.database.dao.MovieDao
import com.ducpv.movie.shared.data.database.entity.MovieEntity

/**
 * Created by pvduc9773 on 09/12/2022.
 */
@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private const val databaseName = "movie-db"

        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
        }
    }
}
