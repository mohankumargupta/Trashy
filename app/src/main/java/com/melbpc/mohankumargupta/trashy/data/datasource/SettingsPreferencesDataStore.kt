package com.melbpc.mohankumargupta.trashy.data.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore("settings")

class SettingsPreferencesDataStore(
    private val context: Context
) : SettingsDataSource {

    private companion object {
        val COLLECTION_DAY = stringPreferencesKey("collection_day")
        val INFO_DATE = stringPreferencesKey("info_date") // Storing LocalDate as String
        val LAST_COLLECTION_BIN_TYPE =
            stringPreferencesKey("last_collection_bin_type") // Storing BinType as String
        val RECYCLING_LID_COLOR =
            stringPreferencesKey("recycling_lid_color") // Storing ColorSwatch enum name as String
        val GARDEN_LID_COLOR =
            stringPreferencesKey("garden_lid_color") // Storing ColorSwatch enum name as String
    }

    override suspend fun load(): CollectionInfo? {
       return null
    }

    override suspend fun save(settings: CollectionInfo) {
        context.dataStore.edit { preferences ->
            preferences[COLLECTION_DAY] = settings.collectionDay
            preferences[INFO_DATE] = settings.infoDate.toString() // Store LocalDate as ISO-8601 string
            preferences[LAST_COLLECTION_BIN_TYPE] = settings.lastCollectionBinType.name // Store enum name
            preferences[RECYCLING_LID_COLOR] = settings.recyclingLidColor.name // Store enum name
            preferences[GARDEN_LID_COLOR] = settings.gardenLidColor.name // Store enum name
        }
    }

    override suspend fun isEmpty(): Boolean {
        return context.dataStore.data.first().asMap().isEmpty()
    }

}