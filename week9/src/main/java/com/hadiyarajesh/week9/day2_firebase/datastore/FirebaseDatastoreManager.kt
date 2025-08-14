package com.hadiyarajesh.week9.day2_firebase.datastore

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

val Context.firebaseDatastore: DataStore<Preferences> by preferencesDataStore(name = "my_firebase_datastore")

class DatastoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val authenticatedKey = booleanPreferencesKey("authenticated")

    val authenticated: Flow<Boolean> = context.firebaseDatastore.data.map { preference ->
        preference[authenticatedKey] ?: false
    }

    suspend fun saveUserAuthenticated(authenticated: Boolean) {
        context.firebaseDatastore.edit { preference ->
            preference[authenticatedKey] = authenticated
        }
    }

    suspend fun removeUserAuthenticated() {
        context.firebaseDatastore.edit { preference ->
            preference.remove(authenticatedKey)
        }
    }
}
