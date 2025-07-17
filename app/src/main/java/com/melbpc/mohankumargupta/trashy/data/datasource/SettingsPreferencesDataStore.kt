package com.melbpc.mohankumargupta.trashy.data.datasource

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo

val Context.dataStore by preferencesDataStore("settings")

class SettingsPreferencesDataStore(
    private val context: Context
) : SettingsDataSource {
    override suspend fun load(): CollectionInfo? {
        return null
    }

    override suspend fun save(settings: CollectionInfo) {
        context.dataStore
    }

}