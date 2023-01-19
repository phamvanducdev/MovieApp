package com.ducpv.movie.domain.repository

import com.ducpv.movie.domain.model.Credits
import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.service.MovieService
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by ducpv on 26/07/2022.
 */
interface NetworkMovieRepository {
    fun getMoviesShowing(): Flow<List<Movie>>
    fun getMoviesPopular(): Flow<List<Movie>>
    fun getMovieDetail(movieId: String): Flow<Movie>
    fun getMovieCredits(movieId: String): Flow<Credits>
}

class NetworkMovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : NetworkMovieRepository {
    override fun getMoviesShowing(): Flow<List<Movie>> = flow {
        emit(movieService.getMoviesShowing().results)
    }

    override fun getMoviesPopular(): Flow<List<Movie>> = flow {
        emit(movieService.getMoviesPopular().results)
    }

    override fun getMovieDetail(movieId: String): Flow<Movie> = flow {
        emit(movieService.getMovieDetail(movieId))
    }

    override fun getMovieCredits(movieId: String): Flow<Credits> = flow {
        emit(movieService.getMovieCredits(movieId))
    }
}
