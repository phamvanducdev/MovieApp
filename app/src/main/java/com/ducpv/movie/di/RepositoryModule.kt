package com.ducpv.movie.di

import android.content.Context
import com.ducpv.movie.preferstore.PrefsDataStoreRepository
import com.ducpv.movie.preferstore.PrefsDataStoreRepositoryImpl
import com.ducpv.movie.preferstore.dataStore
import com.ducpv.movie.repository.Repository
import com.ducpv.movie.repository.RepositoryImpl
import com.ducpv.movie.service.Service
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by pvduc9773 on 26/07/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesPrefsDataStoreRepository(
        @ApplicationContext context: Context,
        gson: Gson
    ): PrefsDataStoreRepository {
        return PrefsDataStoreRepositoryImpl(context.dataStore, gson)
    }

    @Provides
    @Singleton
    fun providesRepository(service: Service): Repository {
        return RepositoryImpl(service)
    }
}
