package com.hadiyarajesh.week9.day2_firebase.authentication

import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.hadiyarajesh.week9.day2_firebase.datastore.DatastoreRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.Instant
import javax.inject.Inject

interface AuthenticationRepository {
    fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun signOut()
}

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val analytics: FirebaseAnalytics,
    private val datastoreRepository: DatastoreRepository
) : AuthenticationRepository {
    override fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                authResult.user?.let { user ->
                    GlobalScope.launch {
                        saveUserToFirestore(user)
                        saveUserAuthenticated()
                        onSuccess()
                    }
                }
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    override fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                GlobalScope.launch {
                    saveUserAuthenticated()
                    onSuccess()
                }
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    override fun signOut() {
        auth.signOut()
    }

    private suspend fun saveUserToFirestore(user: FirebaseUser) {
        val usersCollectionReference = firestore.collection("users")

        val userDocumentReference = usersCollectionReference.document(user.uid)

        val myFirestoreUser = MyFirestoreUser(
            uid = user.uid,
            email = user.email,
            displayName = user.displayName,
            createdAt = Instant.now().toString()
        )

        val userExits = userDocumentReference.get().await().exists()
        Log.d("TAG", "userExits: $userExits")

        if (!userExits) {
            userDocumentReference.set(myFirestoreUser).await()
        }

        trackSignUpEvent(myFirestoreUser.uid, myFirestoreUser.email.toString())
    }

    private fun trackSignUpEvent(userId: String, email: String) {
//        analytics.logEvent("sign_up") {
        analytics.logEvent(FirebaseAnalytics.Event.SIGN_UP) {
//            param(FirebaseAnalytics.Param.ITEM_ID, userId)
            param("user_id", userId)
            param("email", email)
        }
    }

    private suspend fun saveUserAuthenticated() {
        datastoreRepository.saveUserAuthenticated(true)
    }
}

data class MyFirestoreUser(
    val uid: String,
    val email: String?,
    val displayName: String? = null,
    val createdAt: String
)
