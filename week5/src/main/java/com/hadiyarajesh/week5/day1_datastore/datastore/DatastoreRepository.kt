package com.hadiyarajesh.week5.day1_datastore.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DatastoreRepository {
    val isUserAuthenticated: Flow<Boolean>
    suspend fun saveUserAuthenticated(authenticated: Boolean)
    suspend fun clearIsUserAuthenticated()
}

class DatastoreRepositoryImpl @Inject constructor(
    private val datastoreManager: DatastoreManager
) : DatastoreRepository {
    override val isUserAuthenticated: Flow<Boolean> = datastoreManager.isUserAuthenticated

    override suspend fun saveUserAuthenticated(authenticated: Boolean) {
        datastoreManager.saveUserAuthenticated(authenticated)
    }

    override suspend fun clearIsUserAuthenticated() {
        datastoreManager.clearIsUserAuthenticated()
    }
}
