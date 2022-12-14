package com.ducpv.movie.di

import android.content.Context
import com.ducpv.movie.shared.data.prefs.PreferenceStorage
import com.ducpv.movie.shared.data.prefs.SharedPreferenceStorage
import com.ducpv.movie.shared.network.ConnectivityManagerNetworkMonitor
import com.ducpv.movie.shared.network.NetworkMonitor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by pvduc9773 on 25/07/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesContext(@ApplicationContext context: Context): Context = context.applicationContext

    @Singleton
    @Provides
    fun providesPreferenceStorage(context: Context): PreferenceStorage = SharedPreferenceStorage(context)

    @Singleton
    @Provides
    fun providesNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor = ConnectivityManagerNetworkMonitor(context)
}
