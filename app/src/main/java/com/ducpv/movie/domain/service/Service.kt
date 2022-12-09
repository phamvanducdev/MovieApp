package com.ducpv.movie.domain.service

import com.ducpv.movie.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface Service {
    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(): ApiResults<Movie>

    @GET("movie/popular")
    suspend fun getMoviesPopular(): ApiResults<Movie>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: String): Movie
}
