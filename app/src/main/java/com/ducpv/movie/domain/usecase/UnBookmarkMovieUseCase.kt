package com.ducpv.movie.domain.usecase

import com.ducpv.movie.shared.data.database.dao.MovieDao
import com.ducpv.movie.shared.di.DefaultDispatcher
import com.ducpv.movie.shared.usecase.SuspendUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by pvduc9773 on 15/12/2022.
 */
class UnBookmarkMovieUseCase @Inject constructor(
    @DefaultDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val movieDao: MovieDao
) : SuspendUseCase<List<String>, Unit>(coroutineDispatcher) {
    override suspend fun execute(parameters: List<String>) {
        movieDao.deleteMovies(ids = parameters)
    }
}
