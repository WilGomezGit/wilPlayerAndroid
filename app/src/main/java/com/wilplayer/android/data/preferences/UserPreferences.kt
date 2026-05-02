package com.wilplayer.android.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class UserPreferences @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        val YOUTUBE_API_KEY = stringPreferencesKey("youtube_api_key")
        val SHUFFLE_QUALITY = stringPreferencesKey("shuffle_quality")
        val REGION_CODE = stringPreferencesKey("region_code")
    }

    val apiKey: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[YOUTUBE_API_KEY]
    }

    val shuffleQuality: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[SHUFFLE_QUALITY] ?: "MEDIUM"
    }

    val regionCode: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[REGION_CODE] ?: "CO"
    }

    suspend fun saveApiKey(key: String) {
        context.dataStore.edit { preferences ->
            preferences[YOUTUBE_API_KEY] = key
        }
    }

    suspend fun saveShuffleQuality(quality: String) {
        context.dataStore.edit { preferences ->
            preferences[SHUFFLE_QUALITY] = quality
        }
    }

    suspend fun saveRegionCode(code: String) {
        context.dataStore.edit { preferences ->
            preferences[REGION_CODE] = code
        }
    }
}
