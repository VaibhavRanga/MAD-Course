package com.hadiyarajesh.week5.day2_room_database

import androidx.room.Embedded
import androidx.room.Relation
import com.hadiyarajesh.week5.day2_room_database.aadhaar.AadhaarCard
import com.hadiyarajesh.week5.day2_room_database.user.User

// This is an intermediate re-presentation of the User and AadhaarCard entities.
data class UserAndAadhaarCard(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val aadhaarCard: AadhaarCard
)
