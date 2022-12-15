package com.ducpv.movie.di

import android.content.Context
import com.ducpv.movie.shared.data.prefs.PreferenceStorage
import com.ducpv.movie.shared.data.prefs.SharedPreferenceStorage
import com.ducpv.movie.shared.network.ConnectivityManagerNetworkMonitor
import com.ducpv.movie.shared.network.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ducpv on 25/07/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor = ConnectivityManagerNetworkMonitor(context)

    @Singleton
    @Provides
    fun providesPreferenceStorage(@ApplicationContext context: Context): PreferenceStorage = SharedPreferenceStorage(context)
}
