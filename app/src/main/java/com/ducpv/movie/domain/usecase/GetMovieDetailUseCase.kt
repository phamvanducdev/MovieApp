package com.ducpv.movie.domain.usecase

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.repository.NetworkMovieRepository
import com.ducpv.movie.domain.repository.OfflineMovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

/**
 * Created by ducpv on 29/11/2022.
 */
class GetMovieDetailUseCase @Inject constructor(
    private val offlineMovieRepository: OfflineMovieRepository,
    private val networkMovieRepository: NetworkMovieRepository
) {
    operator fun invoke(movieId: String): Flow<Movie> = combine(
        networkMovieRepository.getMovieDetail(movieId),
        networkMovieRepository.getMovieCredits(movieId),
        offlineMovieRepository.getMovies()
    ) { movie, credits, moviesBookmarked ->
        movie.apply {
            casts = credits.casts
            isBookmarked = moviesBookmarked.any { it.id == movieId }
        }
        return@combine movie
    }
}
