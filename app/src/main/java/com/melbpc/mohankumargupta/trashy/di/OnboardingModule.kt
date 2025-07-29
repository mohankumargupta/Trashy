package com.melbpc.mohankumargupta.trashy.di

import com.melbpc.mohankumargupta.trashy.data.repository.OnboardingRepository
import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepository
import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepositoryInterface
import com.melbpc.mohankumargupta.trashy.di.SettingsModule.provideSettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {
    @Provides
    @Singleton
    fun provideOnboardingRepository(
        settingsRepository: SettingsRepositoryInterface
    ): OnboardingRepository {
        return OnboardingRepository(settingsRepository)
    }
}