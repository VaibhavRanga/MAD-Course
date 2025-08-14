package com.hadiyarajesh.week5.day1_datastore.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "my_datastore")

class DatastoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val authenticatedKey = booleanPreferencesKey("authenticated")

    val isUserAuthenticated: Flow<Boolean> = context.datastore.data.map { preference ->
        preference[authenticatedKey] ?: false
    }

    suspend fun saveUserAuthenticated(authenticated: Boolean) {
        context.datastore.edit { preference ->
            preference[authenticatedKey] = authenticated
        }
    }

    suspend fun clearIsUserAuthenticated() {
        context.datastore.edit { preference ->
            preference.remove(authenticatedKey)
        }
    }
}
