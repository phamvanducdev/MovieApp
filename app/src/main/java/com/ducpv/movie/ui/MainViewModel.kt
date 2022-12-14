package com.ducpv.movie.ui

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ducpv.movie.shared.base.BaseViewModel
import com.ducpv.movie.shared.network.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

/**
 * Created by pvduc9773 on 26/07/2022.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    networkMonitor: NetworkMonitor
) : BaseViewModel() {
    val networkState = networkMonitor.isOnline.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = true
    ).asLiveData()
}
