package com.melbpc.mohankumargupta.trashy.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(): ViewModel() {
    private val onboardingFirstRoute = RouteOnboardingCollectionDay
    private val homeRoute = RouteHome(BinType.RECYCLING, ColorSwatch.Blue)
    private val _navKey = MutableStateFlow<NavigationRoute>(onboardingFirstRoute)
    val navKey: StateFlow<NavigationRoute>  = _navKey

    val isOnboardingComplete: StateFlow<Boolean> = flow {
        delay(1)
        emit(false)
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        true,
    )



}

