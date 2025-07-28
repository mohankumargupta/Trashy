package com.melbpc.mohankumargupta.trashy.domain

import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepositoryInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class OnboardingRequiredUseCase @Inject constructor(
    val settingsRepository: SettingsRepositoryInterface
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<Boolean> {
        return settingsRepository.load().mapLatest { collectionInfo ->
            settingsRepository.isOnboardingComplete()
        }
    }
}

