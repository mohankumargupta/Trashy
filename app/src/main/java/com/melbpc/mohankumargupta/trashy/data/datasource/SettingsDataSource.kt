package com.melbpc.mohankumargupta.trashy.data.datasource

import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo

interface SettingsDataSource {
    suspend fun load(): CollectionInfo?
    suspend fun save(settings: CollectionInfo)
}