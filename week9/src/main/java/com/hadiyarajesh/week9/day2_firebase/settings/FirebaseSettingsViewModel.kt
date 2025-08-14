package com.hadiyarajesh.week9.day2_firebase.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.week9.day2_firebase.authentication.AuthenticationRepository
import com.hadiyarajesh.week9.day2_firebase.datastore.DatastoreRepository
import com.hadiyarajesh.week9.day2_firebase.navigation.NavDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseSettingsViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository,
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    private val _startDestination = MutableStateFlow<NavDestination>(NavDestination.SignIn)
    val startDestination: StateFlow<NavDestination> get() = _startDestination

    init {
        getStartDestination()
    }

    private fun getStartDestination() {
        viewModelScope.launch {
            datastoreRepository.authenticated.collect { authenticated ->
                _startDestination.value =
                    if (authenticated) NavDestination.Home else NavDestination.SignIn
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authenticationRepository.signOut()
            datastoreRepository.removeUserAuthenticated()
        }
    }
}
