package com.hadiyarajesh.week5.day2_room_database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.week5.day2_room_database.aadhaar.AadhaarCard
import com.hadiyarajesh.week5.day2_room_database.aadhaar.AadhaarCardRepository
import com.hadiyarajesh.week5.day2_room_database.user.Address
import com.hadiyarajesh.week5.day2_room_database.user.User
import com.hadiyarajesh.week5.day2_room_database.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val aadhaarCardRepository: AadhaarCardRepository
) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users.asStateFlow()

    //    val usersFlow: StateFlow<List<User>> = userRepository.getAllUsersFlow()
    val usersAndAadhaarCardFlow: StateFlow<List<UserAndAadhaarCard>> =
        userRepository.getAllUsersWithAadhaarCardFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    fun addUser(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val user = User(
                name = "Rajesh",
                email = "hadiyarajesh@hadiyarajesh.com",
                password = "password",
                address = getRandomAddress(),
                createdAt = Instant.now()
            )
            val userId = userRepository.insertUser(user)

            val aadhaarCard = AadhaarCard(
                id = 0,
                number = getRandomAadhaarCardNumber(),
                userId = userId
            )
            aadhaarCardRepository.insertAadhaarCard(aadhaarCard)

            onSuccess()
        }
    }

    fun getAllUsers() {
        viewModelScope.launch {
            val retrievedUsers = userRepository.getAllUsers()
            _users.value = retrievedUsers
        }
    }

    fun deleteUser(user: User, onSuccess: () -> Unit) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
            onSuccess()
        }
    }

    fun getAllUsersFlow() {
        viewModelScope.launch {
            userRepository.getAllUsersFlow().collect { retrievedUsers ->
                _users.value = retrievedUsers
            }
        }
    }
}

fun getRandomAddress(): Address {
    val cities = listOf("Bengaluru", "Mumbai", "Delhi", "Kolkata")
    val states = listOf("Karnataka", "Maharashtra", "Delhi", "West Bengal")
    val randomCity = cities.random()
    val randomState = states.random()
    return Address(randomCity, randomState)
}

fun getRandomAadhaarCardNumber(): String {
    // 1234-5678-9012-3456
    val allowedNumbers = (0..9)

    return buildString {
        val totalLoopCount = 4
        repeat(totalLoopCount) { index ->
            val fourDigitString = buildString {
                repeat(4) {
                    append(allowedNumbers.random())
                }
            }

            append(fourDigitString)
            if (index != totalLoopCount - 1) {
                append("-")
            }
        }
    }
}
