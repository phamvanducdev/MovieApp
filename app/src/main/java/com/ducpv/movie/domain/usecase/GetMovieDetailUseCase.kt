package com.ducpv.movie.domain.usecase

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.repository.NetworkMovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by ducpv on 29/11/2022.
 */
class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: NetworkMovieRepository
) {
    operator fun invoke(movieId: String): Flow<Movie> {
        return movieRepository.getMovieDetail(movieId)
    }
}
