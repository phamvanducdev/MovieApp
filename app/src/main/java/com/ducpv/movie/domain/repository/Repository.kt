package com.ducpv.movie.domain.repository

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.service.Service
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface Repository {
    suspend fun getMoviesNowPlaying(): List<Movie>
    suspend fun getMoviesPopular(): List<Movie>
    suspend fun getMovieDetail(movieId: String): Movie
}

class RepositoryImpl @Inject constructor(
    private val service: Service
) : Repository {
    override suspend fun getMoviesNowPlaying(): List<Movie> {
        return service.getMoviesNowPlaying().results
    }

    override suspend fun getMoviesPopular(): List<Movie> {
        return service.getMoviesPopular().results
    }

    override suspend fun getMovieDetail(movieId: String): Movie {
        return service.getMovieDetail(movieId)
    }
}
