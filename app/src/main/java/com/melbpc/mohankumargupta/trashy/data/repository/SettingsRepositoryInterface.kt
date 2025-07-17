package com.melbpc.mohankumargupta.trashy.data.repository

import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo

interface SettingsRepositoryInterface {
    suspend fun load(): CollectionInfo
    suspend fun save(settings: CollectionInfo)

    suspend fun isOnboardingComplete(): Boolean
}

