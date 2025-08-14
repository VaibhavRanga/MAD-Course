package com.hadiyarajesh.week5.day2_room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hadiyarajesh.week5.day2_room_database.aadhaar.AadhaarCard
import com.hadiyarajesh.week5.day2_room_database.aadhaar.AadhaarCardDao
import com.hadiyarajesh.week5.day2_room_database.user.User
import com.hadiyarajesh.week5.day2_room_database.user.UserDao

@Database(
    entities = [User::class, AadhaarCard::class],
    version = 1
)
//@AutoMigration(from = 1, to = 2)
@TypeConverters(InstantTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getAadhaarCardDao(): AadhaarCardDao

    class MyExampleAutoMigration : AutoMigrationSpec {
        @Override
        override fun onPostMigrate(db: SupportSQLiteDatabase) {
            // Invoked once auto migration is done
            db.execSQL("ALTER TABLE User ADD COLUMN last_updated INTEGER NOT NULL DEFAULT 0")
        }
    }
}
