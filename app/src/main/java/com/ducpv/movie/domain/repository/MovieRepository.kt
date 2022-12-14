package com.ducpv.movie.domain.repository

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.service.MovieService
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface MovieRepository {
    fun getMoviesNowPlaying(): Flow<List<Movie>>
    fun getMoviesPopular(): Flow<List<Movie>>
    fun getMovieDetail(movieId: String): Flow<Movie>
}

class MovieDataSource @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {
    override fun getMoviesNowPlaying(): Flow<List<Movie>> = flow {
        emit(movieService.getMoviesNowPlaying().results)
    }

    override fun getMoviesPopular(): Flow<List<Movie>> = flow {
        emit(movieService.getMoviesPopular().results)
    }

    override fun getMovieDetail(movieId: String): Flow<Movie> = flow {
        emit(movieService.getMovieDetail(movieId))
    }
}
