package com.hadiyarajesh.week5.day2_room_database.aadhaar

import androidx.room.Dao
import androidx.room.Upsert

/**
 * Data Access Object for the AadhaarCard entity.
 */
@Dao
interface AadhaarCardDao {
    /**
     * Inserts or updates an AadhaarCard in the database.
     * If the AadhaarCard already exists, it will be updated. Otherwise, it will be inserted.
     *
     * @param aadhaarCard The AadhaarCard to insert or update.
     * @return The row ID of the inserted or updated AadhaarCard.
     */
    @Upsert
    suspend fun insertOrUpdate(aadhaarCard: AadhaarCard): Long
}
