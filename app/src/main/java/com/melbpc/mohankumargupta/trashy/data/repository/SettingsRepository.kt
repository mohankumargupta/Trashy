package com.melbpc.mohankumargupta.trashy.data.repository

import com.melbpc.mohankumargupta.trashy.data.model.BinType
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val collectionDay: Flow<String>
    val lastBinType: Flow<BinType>
    val recyclingLidColor: Flow<String>
    val gardenLidColor: Flow<String>
    val isOnboarded: Flow<Boolean>

    suspend fun saveOnboarding(
        day: String,
        lastBinType: BinType,
        recyclingColor: String,
        gardenColor: String
    )

}

