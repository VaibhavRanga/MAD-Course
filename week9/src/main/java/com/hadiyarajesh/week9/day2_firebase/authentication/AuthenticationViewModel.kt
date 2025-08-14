package com.hadiyarajesh.week9.day2_firebase.authentication

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {
    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val authUiState: StateFlow<AuthUiState> get() = _authUiState.asStateFlow()

    fun signUp(email: String, password: String) {
        _authUiState.value = AuthUiState.Loading

        repository.signUp(
            email = email,
            password = password,
            onSuccess = {
                _authUiState.value = AuthUiState.Success
            },
            onFailure = {
                _authUiState.value = AuthUiState.Error(it.message ?: "Something went wrong")
            }
        )
    }

    fun signIn(email: String, password: String) {
        _authUiState.value = AuthUiState.Loading

        repository.signIn(
            email = email,
            password = password,
            onSuccess = {
                _authUiState.value = AuthUiState.Success
            }, onFailure = {
                _authUiState.value = AuthUiState.Error(it.message ?: "Something went wrong")
            }
        )
    }
}

sealed interface AuthUiState {
    object Initial : AuthUiState
    object Loading : AuthUiState
    object Success : AuthUiState
    data class Error(val message: String) : AuthUiState
}
