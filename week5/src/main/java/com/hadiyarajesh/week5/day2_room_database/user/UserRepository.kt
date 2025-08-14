package com.hadiyarajesh.week5.day2_room_database.user

import com.hadiyarajesh.week5.day2_room_database.AppDatabase
import com.hadiyarajesh.week5.day2_room_database.UserAndAadhaarCard
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {
    suspend fun insertUser(user: User): Long {
        return appDatabase.getUserDao().insertUser(user)
    }

    suspend fun getAllUsers(): List<User> {
        return appDatabase.getUserDao().getAllUsers()
    }

    suspend fun deleteUser(user: User) {
        appDatabase.getUserDao().deleteUser(user)
    }

    fun getAllUsersFlow(): Flow<List<User>> {
        return appDatabase.getUserDao().getAllUsersFlow()
    }

    fun getAllUsersWithAadhaarCardFlow(): Flow<List<UserAndAadhaarCard>> {
        return appDatabase.getUserDao().getAllUsersWithAadhaarCard()
    }
}
