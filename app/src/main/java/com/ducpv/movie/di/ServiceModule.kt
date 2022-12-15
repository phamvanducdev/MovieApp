package com.ducpv.movie.di

import com.ducpv.movie.BuildConfig
import com.ducpv.movie.domain.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ducpv on 26/07/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                } else {
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            )
            .addInterceptor(
                Interceptor { chain ->
                    val originalRequest = chain.request()
                    val originalUrl = originalRequest.url
                    val requestUrl = originalUrl
                        .newBuilder()
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build()
                    val requestBuilder = originalRequest
                        .newBuilder()
                        .url(requestUrl)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
            )

        return okHttpClient
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)
}
