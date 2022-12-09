package com.ducpv.movie.di

import com.ducpv.movie.domain.repository.Repository
import com.ducpv.movie.domain.repository.RepositoryImpl
import com.ducpv.movie.domain.service.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by pvduc9773 on 26/07/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesRepository(service: Service): Repository {
        return RepositoryImpl(service)
    }
}
