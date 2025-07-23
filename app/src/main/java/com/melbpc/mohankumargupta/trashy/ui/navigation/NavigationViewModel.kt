package com.melbpc.mohankumargupta.trashy.ui.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

    val isOnboardingComplete: StateFlow<Boolean> = flow<Boolean> {
        delay(1)
        //emit(false)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        false,
    )

    val initialRoute: StateFlow<NavigationRoute> = isOnboardingComplete.map { complete ->
        if (complete) RouteHome(BinType.RECYCLING, ColorSwatch.Black) else RouteOnboardingCollectionDay

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RouteInitialScreen
    )

    fun getInitialHomeRouteParams(): RouteHome {
        return RouteHome(BinType.RECYCLING, ColorSwatch.Black)
    }

    fun getOnboardingHomeRouteParams(): RouteHome {
        return RouteHome(BinType.GARDEN, ColorSwatch.Red)
    }


    fun navigateHome(backstack: SnapshotStateList<NavigationRoute>) {

    }

    fun navigateInitial(backstack: SnapshotStateList<NavigationRoute>) {

    }


}





