package com.ducpv.movie.preferstore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ducpv.movie.extension.fromJson
import com.google.gson.Gson
import javax.inject.Inject
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

/**
 * Created by pvduc9773 on 08/08/2022.
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "movie-preferences")

interface PrefsDataStoreRepository {
    suspend fun setAccessToken(accessToken: String)
    suspend fun getAccessToken(): String?
}

class PrefsDataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson
) : PrefsDataStoreRepository {
    companion object {
        private val PREFERENCES_KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    private suspend fun clear(key: Preferences.Key<String>) {
        dataStore.edit { it.remove(key) }
    }

    private suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        dataStore.edit { it[key] = value }
    }

    private suspend fun <T> get(key: Preferences.Key<T>): T? {
        return dataStore.data.map { it[key] }.firstOrNull()
    }

    private suspend fun setObject(key: Preferences.Key<String>, value: Any) {
        set(key, gson.toJson(value))
    }

    private suspend inline fun <reified T> getObject(key: Preferences.Key<String>): T? {
        val json = get(key)
        return gson.fromJson<T>(json)
    }

    override suspend fun setAccessToken(accessToken: String) {
        set(PREFERENCES_KEY_ACCESS_TOKEN, accessToken)
    }

    override suspend fun getAccessToken(): String? {
        return get(PREFERENCES_KEY_ACCESS_TOKEN)
    }
}
