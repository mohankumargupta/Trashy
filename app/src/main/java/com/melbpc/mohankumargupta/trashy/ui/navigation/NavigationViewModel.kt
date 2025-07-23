package com.melbpc.mohankumargupta.trashy.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(): ViewModel() {

    val isOnboardingComplete: StateFlow<Boolean> = flow {
        delay(1)
        emit(false)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        true,
    )

}

