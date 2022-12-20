package com.ducpv.movie.domain.usecase

import com.ducpv.movie.domain.repository.OfflineMovieRepository
import javax.inject.Inject

/**
 * Created by ducpv on 15/12/2022.
 */
class UnBookmarkMovieUseCase @Inject constructor(
    private val movieRepository: OfflineMovieRepository
) {
    suspend operator fun invoke(movieId: String) {
        movieRepository.removeMovies(ids = listOf(movieId))
    }
}
