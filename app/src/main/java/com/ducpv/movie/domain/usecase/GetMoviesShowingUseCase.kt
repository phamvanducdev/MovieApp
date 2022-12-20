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
class GetMoviesShowingUseCase @Inject constructor(
    private val offlineMovieRepository: OfflineMovieRepository,
    private val networkMovieRepository: NetworkMovieRepository
) {
    operator fun invoke(): Flow<List<Movie>> = combine(
        networkMovieRepository.getMoviesShowing(),
        offlineMovieRepository.getMovies()
    ) { networkMovies, moviesBookmarked ->
        networkMovies.map { movie ->
            movie.apply {
                isBookmarked = movie.id in moviesBookmarked.map(Movie::id)
            }
        }
    }
}
