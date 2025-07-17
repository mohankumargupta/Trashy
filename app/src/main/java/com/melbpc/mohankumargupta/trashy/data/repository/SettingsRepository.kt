package com.melbpc.mohankumargupta.trashy.data.repository

import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo

class SettingsRepository: SettingsRepositoryInterface {
    override suspend fun load(): CollectionInfo {
        TODO("Not yet implemented")
    }

    override suspend fun save(settings: CollectionInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun isOnboardingComplete(): Boolean {
        return false
    }

}