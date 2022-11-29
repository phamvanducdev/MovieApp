package com.ducpv.movie.repository

import com.ducpv.movie.model.Movie
import com.ducpv.movie.service.Service
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface Repository {
    suspend fun getMoviesNowPlaying(): List<Movie>
    suspend fun getMoviesPopular(): List<Movie>
}

class RepositoryImpl @Inject constructor(
    private val service: Service
) : Repository, BaseRepository() {
    override suspend fun getMoviesNowPlaying(): List<Movie> {
        return service.getMoviesNowPlaying(apiKey = apiKey).results
    }

    override suspend fun getMoviesPopular(): List<Movie> {
        return service.getMoviesPopular(apiKey = apiKey).results
    }
}
