package com.ducpv.movie.domain.usecase

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.repository.MovieRepository
import com.ducpv.movie.shared.di.DefaultDispatcher
import com.ducpv.movie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

/**
 * Created by pvduc9773 on 29/11/2022.
 */
class GetMoviesNowPlayingUseCase @Inject constructor(
    @DefaultDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val movieRepository: MovieRepository
) : FlowUseCase<Unit, List<Movie>>(coroutineDispatcher) {
    override fun execute(parameters: Unit): Flow<List<Movie>> {
        return movieRepository.getMoviesNowPlaying()
    }
}
