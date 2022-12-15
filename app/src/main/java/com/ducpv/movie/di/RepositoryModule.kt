package com.ducpv.movie.di

import com.ducpv.movie.domain.repository.MovieRepository
import com.ducpv.movie.domain.repository.MovieDataSource
import com.ducpv.movie.domain.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ducpv on 26/07/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesRepository(movieService: MovieService): MovieRepository = MovieDataSource(movieService)
}
