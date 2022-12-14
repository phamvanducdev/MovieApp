package com.ducpv.movie.di

import com.ducpv.movie.shared.di.DefaultDispatcher
import com.ducpv.movie.shared.di.IoDispatcher
import com.ducpv.movie.shared.di.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by pvduc9773 on 09/12/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
