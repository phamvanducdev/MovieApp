package com.ducpv.movie.di

import android.content.Context
import com.ducpv.movie.shared.data.database.AppDatabase
import com.ducpv.movie.shared.data.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by pvduc9773 on 25/07/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.buildDatabase(context)

    @Singleton
    @Provides
    fun providesMovieDao(database: AppDatabase): MovieDao = database.movieDao()
}
