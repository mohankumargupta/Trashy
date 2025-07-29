package com.melbpc.mohankumargupta.trashy.data.repository

import androidx.lifecycle.viewModelScope
import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import com.melbpc.mohankumargupta.trashy.ui.onboarding.ScheduleIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class OnboardingRepository(
    private val settingsRepository: SettingsRepositoryInterface,
) {
    private val _uiState = MutableStateFlow(CollectionInfo(infoDate = LocalDate.now()))
    val uiState: StateFlow<CollectionInfo>  = _uiState.asStateFlow()

    fun handle(intent: ScheduleIntent) {
        _uiState.update { current ->
            when (intent) {
                is ScheduleIntent.DayChosen -> current.copy(collectionDay = intent.day)
                is ScheduleIntent.GardenLidColor -> {
                    current.copy(gardenLidColor = intent.color)
                }
                is ScheduleIntent.RecyclingLidColor -> current.copy(recyclingLidColor = intent.color)
                is ScheduleIntent.LastBinType -> {
                    current.copy(
                        lastCollectionBinType = intent.type,
                        infoDate = LocalDate.now()
                    )
                }
            }
        }
    }

    suspend fun save() {
        withContext(Dispatchers.IO) {
            settingsRepository.save(uiState.value)
        }
    }

}