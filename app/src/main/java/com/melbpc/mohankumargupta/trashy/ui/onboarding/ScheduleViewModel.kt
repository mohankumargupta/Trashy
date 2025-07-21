package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import com.melbpc.mohankumargupta.trashy.ui.navigation.RouteOnboardingCollectionDay
import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepositoryInterface
import com.melbpc.mohankumargupta.trashy.ui.navigation.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val settingsRepository: SettingsRepositoryInterface
) : ViewModel() {
    private val _uiState = MutableStateFlow(CollectionInfo())
    val uiState: StateFlow<CollectionInfo>  = _uiState
    private val _navKey = MutableStateFlow<NavigationRoute>(RouteOnboardingCollectionDay)
    val navKey: StateFlow<NavigationRoute>  = _navKey

    fun handle(intent: ScheduleIntent) {
        _uiState.update { current ->
            when (intent) {
                is ScheduleIntent.DayChosen -> current.copy(collectionDay = intent.day)
                is ScheduleIntent.GardenLidColor -> {
                    current.copy(gardenLidColor = intent.color)
                }
                is ScheduleIntent.RecyclingLidColor -> current.copy(recyclingLidColor = intent.color)
                is ScheduleIntent.LastBinType -> {
                    current.copy(lastCollectionBinType = intent.type)
                    current.copy(infoDate = LocalDate.now())
                }
            }
        }
    }

    fun handleFinalOnboardingScreen(intent: ScheduleIntent) {
        handle(intent)
        save()
    }

    private fun save() {
        viewModelScope.launch {
            //settingsRepository.save(uiState.value)
        }
    }
}

