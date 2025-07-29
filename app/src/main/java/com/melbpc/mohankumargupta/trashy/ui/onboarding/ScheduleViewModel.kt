package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import com.melbpc.mohankumargupta.trashy.data.repository.OnboardingRepository
import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

//    private val _uiState = MutableStateFlow(CollectionInfo(infoDate = LocalDate.now()))
//    val uiState: StateFlow<CollectionInfo>  = _uiState.asStateFlow()
    //private val _navKey = MutableStateFlow<NavigationRoute>(RouteOnboardingCollectionDay)
    //val navKey: StateFlow<NavigationRoute>  = _navKey

    val uiState = onboardingRepository.uiState

    fun handleFinalOnboardingScreen(intent: ScheduleIntent) {
        onboardingRepository.handle(intent)
        save()
    }

    fun save() {
        viewModelScope.launch {
            onboardingRepository.save()
        }
    }




}

