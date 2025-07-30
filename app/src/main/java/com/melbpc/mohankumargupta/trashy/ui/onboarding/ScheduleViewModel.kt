package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melbpc.mohankumargupta.trashy.data.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    val onboardingRepository: OnboardingRepository
) : ViewModel() {
    val uiState = onboardingRepository.uiState

    fun handle(intent: ScheduleIntent) {
        onboardingRepository.handle(intent)
        if (intent is ScheduleIntent.GardenLidColor) {
            save()
        }
    }

    fun save() {
        viewModelScope.launch {
            onboardingRepository.save()
        }
    }
}
