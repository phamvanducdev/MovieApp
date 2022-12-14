package com.ducpv.movie.shared.network

import kotlinx.coroutines.flow.Flow

/**
 * Created by pvduc9773 on 15/12/2022.
 */
interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}
