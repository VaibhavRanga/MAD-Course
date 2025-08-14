package com.hadiyarajesh.mad_s10.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hadiyarajesh.mad_s10.data.dao.ImageDao
import com.hadiyarajesh.mad_s10.data.entity.Image

@Database(
    version = 1,
    entities = [Image::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}
