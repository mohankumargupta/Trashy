package com.melbpc.mohankumargupta.trashy.ui.navigation

import androidx.lifecycle.ViewModel
import com.melbpc.mohankumargupta.trashy.domain.OnboardingRequiredUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    onboardingRequiredUseCase: OnboardingRequiredUseCase
) : ViewModel() {
    val isOnboardingRequired = onboardingRequiredUseCase()
}
