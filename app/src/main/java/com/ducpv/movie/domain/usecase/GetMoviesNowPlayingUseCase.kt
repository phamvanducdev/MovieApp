package com.ducpv.movie.domain.usecase

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.repository.Repository
import com.ducpv.movie.shared.di.DefaultDispatcher
import com.ducpv.movie.shared.usecase.SuspendUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by pvduc9773 on 29/11/2022.
 */
class GetMoviesNowPlayingUseCase @Inject constructor(
    @DefaultDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val repository: Repository
) : SuspendUseCase<Unit, List<Movie>>(coroutineDispatcher) {
    override suspend fun execute(parameters: Unit): List<Movie> {
        return repository.getMoviesNowPlaying()
    }
}
