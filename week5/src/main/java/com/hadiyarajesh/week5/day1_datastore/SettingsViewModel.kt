package com.hadiyarajesh.week5.day1_datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.week5.day1_datastore.datastore.DatastoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {
    // Convert Flow into StateFlow
    val isUserAuthenticated: StateFlow<Boolean> = datastoreRepository.isUserAuthenticated
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

    fun logout() {
        viewModelScope.launch {
            datastoreRepository.clearIsUserAuthenticated()
        }
    }
}
