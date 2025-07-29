package com.melbpc.mohankumargupta.trashy.data.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import java.time.LocalDate

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

    override fun load(): Flow<CollectionInfo> {
        return context.dataStore.data.map { preferences ->
            val collectionDay = preferences[COLLECTION_DAY] ?: DayOfWeek.MONDAY.name
            val infoDate = preferences[INFO_DATE]?.let { LocalDate.parse(it) } ?: LocalDate.now()
            val lastCollectionBinType =
                preferences[LAST_COLLECTION_BIN_TYPE]?.let { BinType.valueOf(it) }
                    ?: BinType.RECYCLING
            val recyclingLidColor =
                preferences[RECYCLING_LID_COLOR]?.let { ColorSwatch.valueOf(it) }
                    ?: ColorSwatch.Black
            val gardenLidColor =
                preferences[GARDEN_LID_COLOR]?.let { ColorSwatch.valueOf(it) } ?: ColorSwatch.Black

            CollectionInfo(
                collectionDay = DayOfWeek.valueOf(collectionDay),
                infoDate = infoDate,
                lastCollectionBinType = lastCollectionBinType,
                recyclingLidColor = recyclingLidColor,
                gardenLidColor = gardenLidColor
            )
        }
    }

    override suspend fun save(settings: CollectionInfo) {
        context.dataStore.edit { preferences ->
            preferences[COLLECTION_DAY] = settings.collectionDay.name
            preferences[INFO_DATE] =
                settings.infoDate.toString() // Store LocalDate as ISO-8601 string
            preferences[LAST_COLLECTION_BIN_TYPE] =
                settings.lastCollectionBinType.name // Store enum name
            preferences[RECYCLING_LID_COLOR] = settings.recyclingLidColor.name // Store enum name
            preferences[GARDEN_LID_COLOR] = settings.gardenLidColor.name // Store enum name
        }
    }

    override suspend fun isEmpty(): Boolean {
        return context.dataStore.data.first().asMap().isEmpty()
    }

    override suspend fun reset() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    /*
    fun datasource(): Flow<Preferences> {
        return context.dataStore.data
    }
    */
}