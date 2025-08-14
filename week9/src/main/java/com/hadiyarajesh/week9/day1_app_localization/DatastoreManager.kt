package com.hadiyarajesh.week9.day1_app_localization

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "my_datastore")

class DatastoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val languageKey = stringPreferencesKey("language_code")

    val language: Flow<Language> = context.datastore.data.map { preference ->
        val languageCode = preference[languageKey] ?: LANGUAGE_CODE_ENGLISH
        Language(languageCode)
    }

    suspend fun saveLanguage(language: Language) {
        context.datastore.edit { preference ->
            preference[languageKey] = language.languageCode
        }
    }
}
