package com.melbpc.mohankumargupta.trashy.di

import android.content.Context
import com.melbpc.mohankumargupta.trashy.data.datasource.SettingsDataSource
import com.melbpc.mohankumargupta.trashy.data.datasource.SettingsPreferencesDataStore
import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepository
import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {
    @Provides
    @Singleton
    fun provideSettingsRepository(
        dataSource: SettingsDataSource
    ): SettingsRepositoryInterface {
        return SettingsRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideSettingsDataSource(@ApplicationContext ctx: Context): SettingsDataSource {
        return SettingsPreferencesDataStore(ctx)
    }
}