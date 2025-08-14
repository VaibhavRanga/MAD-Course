package com.hadiyarajesh.week9.day1_app_localization.settings

import com.hadiyarajesh.week9.day1_app_localization.DatastoreManager
import com.hadiyarajesh.week9.day1_app_localization.Language
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SettingsRepository {
    val language: Flow<Language>
    suspend fun saveLanguage(language: Language)
}

class SettingsRepositoryImpl @Inject constructor(
    private val datastoreManager: DatastoreManager
) : SettingsRepository {
    override val language: Flow<Language> = datastoreManager.language

    override suspend fun saveLanguage(language: Language) {
        datastoreManager.saveLanguage(language)
    }
}
