package com.hadiyarajesh.week5.day2_room_database

import androidx.room.TypeConverter
import java.time.Instant

class InstantTypeConverter {
    @TypeConverter
    fun fromInstant(instant: Instant): String {
        return instant.toString()
    }

    @TypeConverter
    fun toInstant(str: String): Instant {
        return Instant.parse(str)
    }
}
