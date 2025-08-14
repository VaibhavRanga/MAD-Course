package com.hadiyarajesh.week9.day1_app_localization.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.week9.day1_app_localization.LANGUAGE_CODE_ENGLISH
import com.hadiyarajesh.week9.day1_app_localization.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val language = settingsRepository.language.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Language(LANGUAGE_CODE_ENGLISH)
    )

    fun saveLanguage(language: Language) {
        viewModelScope.launch {
            settingsRepository.saveLanguage(language)
        }
    }
}
