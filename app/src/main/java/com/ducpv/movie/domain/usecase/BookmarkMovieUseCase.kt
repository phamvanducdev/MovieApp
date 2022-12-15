package com.ducpv.movie.domain.usecase

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.shared.data.database.dao.MovieDao
import com.ducpv.movie.shared.data.database.entity.asEntity
import com.ducpv.movie.shared.di.DefaultDispatcher
import com.ducpv.movie.shared.usecase.SuspendUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by pvduc9773 on 15/12/2022.
 */
class BookmarkMovieUseCase @Inject constructor(
    @DefaultDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val movieDao: MovieDao
) : SuspendUseCase<List<Movie>, Unit>(coroutineDispatcher) {
    override suspend fun execute(parameters: List<Movie>) {
        movieDao.insertMovies(entities = parameters.map(Movie::asEntity))
    }
}
