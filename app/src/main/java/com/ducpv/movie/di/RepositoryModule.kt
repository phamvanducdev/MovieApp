package com.ducpv.movie.di

import com.ducpv.movie.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by ducpv on 26/07/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsNetworkMovieRepository(
        movieRepository: NetworkMovieRepositoryImpl
    ): NetworkMovieRepository

    @Binds
    fun bindsOfflineMovieRepository(
        movieRepository: OfflineMovieRepositoryImpl
    ): OfflineMovieRepository
}
