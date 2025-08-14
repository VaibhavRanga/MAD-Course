package com.hadiyarajesh.week2.day2_architecture.model

class GetAllUsersUseCase {
    val repository = UserRepositoryImpl()

    fun getUsers(): List<User> {
        return repository.getAllUsers().filter { it.id % 2 == 0 }
    }
}
