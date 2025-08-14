package com.hadiyarajesh.week5.day2_room_database.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hadiyarajesh.week5.day2_room_database.UserAndAadhaarCard
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    //    @Query("INSERT INTO User values (user.id, user.name)")
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // One-shot write query
    @Upsert // Update + Insert
    suspend fun insertUser(user: User): Long

    // One-shot read query
    @Query("SELECT * From User")
    suspend fun getAllUsers(): List<User>

//    @Query("DELETE FROM User WHERE id = :userId")
//    suspend fun deleteUser(userId: Int)

    @Delete
    suspend fun deleteUser(user: User)

    // Observable read query
    @Query("SELECT * From User")
    fun getAllUsersFlow(): Flow<List<User>>

    //    @Query("SELECT * From User u, AadhaarCard a WHERE u.id = a.userId")
    @Query("SELECT * From User u")
    fun getAllUsersWithAadhaarCard(): Flow<List<UserAndAadhaarCard>>
}
