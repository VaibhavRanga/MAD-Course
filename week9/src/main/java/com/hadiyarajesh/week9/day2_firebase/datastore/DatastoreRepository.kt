package com.hadiyarajesh.week9.day2_firebase.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatastoreRepository @Inject constructor(
    private val datastoreManager: DatastoreManager
) {
    val authenticated: Flow<Boolean> = datastoreManager.authenticated

    suspend fun saveUserAuthenticated(authenticated: Boolean) =
        datastoreManager.saveUserAuthenticated(authenticated)

    suspend fun removeUserAuthenticated() = datastoreManager.removeUserAuthenticated()
}
