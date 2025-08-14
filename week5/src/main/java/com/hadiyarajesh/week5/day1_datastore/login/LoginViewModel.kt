package com.hadiyarajesh.week5.day1_datastore.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.week5.day1_datastore.datastore.DatastoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {
    fun login() {
        viewModelScope.launch {
            delay(3.seconds)
            // login is successful, add user to datastore
            datastoreRepository.saveUserAuthenticated(true)
        }
    }
}
