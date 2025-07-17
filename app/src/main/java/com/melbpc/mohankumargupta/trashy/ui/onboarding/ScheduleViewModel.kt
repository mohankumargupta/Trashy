package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import com.melbpc.mohankumargupta.trashy.ui.navigation.Home
import com.melbpc.mohankumargupta.trashy.ui.navigation.OnboardingCollectionDay
import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepositoryInterface
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
    val uiState: StateFlow<CollectionInfo> = _uiState
    private val _navKey = MutableStateFlow<Any>(OnboardingCollectionDay)
    val navKey: StateFlow<Any> = _navKey

    init {
        viewModelScope.launch {

        }
    }

    fun handle(intent: ScheduleIntent) {
        _uiState.update { current ->
            when (intent) {
                is ScheduleIntent.DayChosen -> current.copy(collectionDay = intent.day)
                is ScheduleIntent.GardenLidColor -> current.copy(gardenLidColor = intent.color)
                is ScheduleIntent.RecyclingLidColor -> current.copy(recyclingLidColor = intent.color)
                is ScheduleIntent.LastBinType -> {
                    current.copy(lastCollectionBinType = intent.type)
                    current.copy(infoDate = LocalDate.now())
                }
            }
        }
    }
}

sealed interface ScheduleIntent {
    data class DayChosen(val day: String) : ScheduleIntent
    data class LastBinType(val type: BinType) : ScheduleIntent
    data class RecyclingLidColor(val color: Color) : ScheduleIntent
    data class GardenLidColor(val color: Color) : ScheduleIntent
}