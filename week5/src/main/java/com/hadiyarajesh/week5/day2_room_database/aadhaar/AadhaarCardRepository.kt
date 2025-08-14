package com.hadiyarajesh.week5.day2_room_database.aadhaar

import com.hadiyarajesh.week5.day2_room_database.AppDatabase
import javax.inject.Inject

class AadhaarCardRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {
    suspend fun insertAadhaarCard(aadhaarCard: AadhaarCard): Long {
        return appDatabase.getAadhaarCardDao().insertOrUpdate(aadhaarCard)
    }
}