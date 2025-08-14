package com.hadiyarajesh.week2.day2_architecture.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.week2.day2_architecture.model.GetAllUsersUseCase
import com.hadiyarajesh.week2.day2_architecture.model.User
import com.hadiyarajesh.week2.day2_architecture.model.UserRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class UserViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    val repository = UserRepositoryImpl()
    val useCase = GetAllUsersUseCase()

    init {
        viewModelScope.launch {
            getUsers()
        }
    }

    private suspend fun getUsers() {
//        val retrievedUsers = repository.getAllUsers()
        val retrievedUsers = useCase.getUsers()
        delay(5.seconds)
        _users.value = retrievedUsers
    }
}
