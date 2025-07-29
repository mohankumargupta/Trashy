package com.melbpc.mohankumargupta.trashy.data.datasource

import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {
    fun load(): Flow<CollectionInfo>
    suspend fun save(settings: CollectionInfo)

    suspend fun isEmpty(): Boolean

    suspend fun reset()
}