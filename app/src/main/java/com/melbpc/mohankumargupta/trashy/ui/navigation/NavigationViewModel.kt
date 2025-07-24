package com.melbpc.mohankumargupta.trashy.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val settingsRepository: SettingsRepositoryInterface
) : ViewModel() {

    val initialRoute = settingsRepository.load().map { collectionInfo ->
        val isOnboardingComplete = settingsRepository.isOnboardingComplete()
        if (isOnboardingComplete) {
            val nextCollectionInfo = collectionInfo.nextBinRecycling()
            val nextBin = if (nextCollectionInfo) BinType.RECYCLING else BinType.GARDEN
            val nextLidColor = if (nextBin == BinType.RECYCLING) collectionInfo.recyclingLidColor else collectionInfo.gardenLidColor
            RouteHome(nextBin, nextLidColor)
        }
        else {
            RouteOnboardingCollectionDay
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RouteOnboardingCollectionDay
    )


//    val initialRoute: StateFlow<NavigationRoute> = isOnboardingComplete.map { complete ->
//        if (complete) {
//            val recordedCollectionInfo = settingsRepository.load()
//            val nextCollectionInfo = recordedCollectionInfo.nextBinRecycling()
//            val nextBin = if (nextCollectionInfo) BinType.RECYCLING else BinType.GARDEN
//            val nextLidColor = if (nextBin == BinType.RECYCLING) recordedCollectionInfo.recyclingLidColor else recordedCollectionInfo.gardenLidColor
//            RouteHome(nextBin, nextLidColor)
//        } else {
//            RouteInitialScreen
//        }
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = RouteOnboardingCollectionDay
//    )


}
