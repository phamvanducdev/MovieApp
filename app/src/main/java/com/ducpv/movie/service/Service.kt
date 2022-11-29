package com.ducpv.movie.service

import com.ducpv.movie.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface Service {
    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(
        @Query("api_key") apiKey: String
    ): ApiResult<List<Movie>>

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("api_key") apiKey: String
    ): ApiResult<List<Movie>>
}
