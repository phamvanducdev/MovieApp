package com.ducpv.movie.domain.usecase

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.shared.data.database.dao.MovieDao
import com.ducpv.movie.shared.data.database.entity.MovieEntity
import com.ducpv.movie.shared.data.database.entity.asExternalModel
import com.ducpv.movie.shared.di.DefaultDispatcher
import com.ducpv.movie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by ducpv on 15/12/2022.
 */
class GetBookmarkMoviesUseCase @Inject constructor(
    @DefaultDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val movieDao: MovieDao
) : FlowUseCase<Unit, List<Movie>>(coroutineDispatcher) {
    override fun execute(parameters: Unit): Flow<List<Movie>> {
        return movieDao.getMovies().map { it.map(MovieEntity::asExternalModel) }
    }
}
