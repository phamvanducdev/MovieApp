package com.ducpv.movie.repository

import com.ducpv.movie.service.Service
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface Repository

class RepositoryImpl @Inject constructor(
    private val service: Service
) : Repository
