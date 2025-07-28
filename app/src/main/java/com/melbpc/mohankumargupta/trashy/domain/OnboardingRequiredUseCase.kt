package com.melbpc.mohankumargupta.trashy.domain

import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnboardingRequiredUseCase(
    val settingsRepository: SettingsRepositoryInterface
) {
    operator fun invoke(): Flow<Boolean> {
        return settingsRepository.load().map { collectionInfo ->
            settingsRepository.isOnboardingComplete()
        }
    }
}

