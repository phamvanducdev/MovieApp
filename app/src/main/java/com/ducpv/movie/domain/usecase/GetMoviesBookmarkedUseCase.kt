package com.ducpv.movie.domain.usecase

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.repository.OfflineMovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by ducpv on 15/12/2022.
 */
class GetMoviesBookmarkedUseCase @Inject constructor(
    private val movieRepository: OfflineMovieRepository
) {
    operator fun invoke(): Flow<List<Movie>> = movieRepository.getMovies()
}
