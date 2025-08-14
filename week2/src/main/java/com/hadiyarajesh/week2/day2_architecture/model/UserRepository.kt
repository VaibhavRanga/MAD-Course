package com.hadiyarajesh.week2.day2_architecture.model

interface UserRepository {
    fun getAllUsers(): List<User>
}

class UserRepositoryImpl : UserRepository {
    override fun getAllUsers(): List<User> = buildList {
        (1..5).forEach { index ->
            add(User(id = index, username = "User $index"))
        }
    }
}

class TestUserRepositoryImpl : UserRepository {
    override fun getAllUsers(): List<User> = buildList {
        (1..2).forEach { index ->
            add(User(id = index, username = "User $index"))
        }
    }
}
