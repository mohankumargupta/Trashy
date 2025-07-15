package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class ScheduleViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CollectionInfo())
    val uiState: StateFlow<CollectionInfo> = _uiState

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