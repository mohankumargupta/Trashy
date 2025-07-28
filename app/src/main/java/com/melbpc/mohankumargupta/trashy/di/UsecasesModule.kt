package com.melbpc.mohankumargupta.trashy.di

import com.melbpc.mohankumargupta.trashy.domain.OnboardingRequiredUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UsecasesModule {
    @Binds
    abstract fun bindOnboardingUseCase(onboardingRequiredUseCase: OnboardingRequiredUseCase): OnboardingRequiredUseCase
}