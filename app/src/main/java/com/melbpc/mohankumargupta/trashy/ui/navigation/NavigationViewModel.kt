package com.melbpc.mohankumargupta.trashy.ui.navigation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch
import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val settingsRepository: SettingsRepositoryInterface
) : ViewModel() {

    val isOnboardingComplete: StateFlow<Boolean> = flow<Boolean> {
        //delay(1)
        //emit(false)
        val isOnboardingComplete = settingsRepository.isOnboardingComplete()
        //Log.d("NavigationViewModel", "isOnboardingComplete: $isOnboardingComplete")
        emit(isOnboardingComplete)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        false,
    )

    val initialRoute: StateFlow<NavigationRoute> = isOnboardingComplete.map { complete ->
        if (complete) {
            val recordedCollectionInfo = settingsRepository.load()
            val nextCollectionInfo = recordedCollectionInfo.nextBinRecycling()
            val nextBin = if (nextCollectionInfo) BinType.RECYCLING else BinType.GARDEN
            val nextLidColor = if (nextBin == BinType.RECYCLING) recordedCollectionInfo.recyclingLidColor else recordedCollectionInfo.gardenLidColor
            RouteHome(nextBin, nextLidColor)
        } else {
            RouteInitialScreen
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RouteOnboardingCollectionDay
    )

    fun getHomeRouteParams(): RouteHome {
        return RouteHome(BinType.RECYCLING, ColorSwatch.Black)
    }

}





