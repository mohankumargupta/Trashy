package com.melbpc.mohankumargupta.trashy.data.repository

import com.melbpc.mohankumargupta.trashy.data.datasource.SettingsDataSource
import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import kotlinx.coroutines.flow.Flow

class SettingsRepository(
    private val dataSource: SettingsDataSource
): SettingsRepositoryInterface {
    override fun load(): Flow<CollectionInfo> {
        return dataSource.load()
    }

    override suspend fun save(settings: CollectionInfo){
        dataSource.save(settings)
    }

    override suspend fun isOnboardingComplete(): Boolean {
        return !dataSource.isEmpty()
    }

    override suspend fun reset() {
        dataSource.reset()

    }

}