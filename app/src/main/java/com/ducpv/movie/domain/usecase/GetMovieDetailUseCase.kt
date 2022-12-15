package com.ducpv.movie.domain.usecase

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.repository.MovieRepository
import com.ducpv.movie.shared.di.DefaultDispatcher
import com.ducpv.movie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

/**
 * Created by ducpv on 29/11/2022.
 */
class GetMovieDetailUseCase @Inject constructor(
    @DefaultDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val movieRepository: MovieRepository
) : FlowUseCase<String, Movie>(coroutineDispatcher) {
    override fun execute(parameters: String): Flow<Movie> {
        return movieRepository.getMovieDetail(parameters)
    }
}
