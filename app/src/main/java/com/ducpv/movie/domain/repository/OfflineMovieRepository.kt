package com.ducpv.movie.domain.repository

import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.shared.data.database.dao.MovieDao
import com.ducpv.movie.shared.data.database.entity.MovieEntity
import com.ducpv.movie.shared.data.database.entity.asEntity
import com.ducpv.movie.shared.data.database.entity.asExternalModel
import com.ducpv.movie.shared.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Created by ducpv on 26/07/2022.
 */
interface OfflineMovieRepository {
    fun getMovies(): Flow<List<Movie>>
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun removeMovies(ids: List<String>)
}

class OfflineMovieRepositoryImpl @Inject constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val movieDao: MovieDao
) : OfflineMovieRepository {
    override suspend fun insertMovies(movies: List<Movie>) {
        withContext(ioDispatcher) {
            movieDao.insertMovieEntities(movies.map(Movie::asEntity))
        }
    }

    override suspend fun removeMovies(ids: List<String>) {
        withContext(ioDispatcher) {
            movieDao.deleteMovieEntities(ids)
        }
    }

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovieEntities().map {
            it.map(MovieEntity::asExternalModel)
        }.flowOn(ioDispatcher)
    }
}
