package com.hadiyarajesh.week5.day2_room_database.user

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    @ColumnInfo(name = "user_password")
    val password: String,
    @Embedded(prefix = "address_")
    val address: Address,
    val createdAt: Instant
)

data class Address(
    val city: String,
    val state: String
)
