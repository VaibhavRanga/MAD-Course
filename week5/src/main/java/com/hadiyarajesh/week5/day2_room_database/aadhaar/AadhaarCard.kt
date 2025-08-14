package com.hadiyarajesh.week5.day2_room_database.aadhaar

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AadhaarCard(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val number: String,
    val userId: Long
)
