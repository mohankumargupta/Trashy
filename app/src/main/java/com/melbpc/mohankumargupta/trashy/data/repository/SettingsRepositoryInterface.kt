package com.melbpc.mohankumargupta.trashy.data.repository

import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import kotlinx.coroutines.flow.Flow

interface SettingsRepositoryInterface {
    fun load(): Flow<CollectionInfo>
    suspend fun save(settings: CollectionInfo)

    suspend fun isOnboardingComplete(): Boolean
}

