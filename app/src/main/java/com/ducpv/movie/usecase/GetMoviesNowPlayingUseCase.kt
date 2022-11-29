package com.ducpv.movie.usecase

import com.ducpv.movie.model.Movie
import com.ducpv.movie.repository.Repository
import javax.inject.Inject

/**
 * Created by pvduc9773 on 29/11/2022.
 */
class GetMoviesNowPlayingUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<List<Movie>>() {
    override suspend fun execute(vararg params: Any): List<Movie> {
        return repository.getMoviesNowPlaying()
    }
}
