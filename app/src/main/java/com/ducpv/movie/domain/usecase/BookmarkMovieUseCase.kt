package com.ducpv.movie.domain.usecase

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.repository.OfflineMovieRepository
import javax.inject.Inject

/**
 * Created by ducpv on 15/12/2022.
 */
class BookmarkMovieUseCase @Inject constructor(
    private val movieRepository: OfflineMovieRepository
) {
    suspend operator fun invoke(movie: Movie) = movieRepository.insertMovies(movies = listOf(movie))
}
