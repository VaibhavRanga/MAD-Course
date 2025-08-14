package com.hadiyarajesh.week9

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.firebase.messaging.FirebaseMessaging
import com.hadiyarajesh.week9.day1_app_localization.settings.SettingsViewModel
import com.hadiyarajesh.week9.day2_firebase.fcm.FCMMessagingService
import com.hadiyarajesh.week9.day2_firebase.fcm.NotificationHelper
import com.hadiyarajesh.week9.day2_firebase.navigation.FirebaseAppNavigation
import com.hadiyarajesh.week9.day2_firebase.settings.FirebaseSettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val settingsViewModel: SettingsViewModel by viewModels()
    val firebaseSettingsViewModel: FirebaseSettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch {
//            settingsViewModel.language.collect { language ->
//                this@MainActivity.updateResources(language)
//            }
//        }

//        setContent {
//            MultiLanguageAppNavigation()
//        }

        setContent {
            val startDestination by firebaseSettingsViewModel.startDestination.collectAsStateWithLifecycle()
            FirebaseAppNavigation(startDestination)
        }

        NotificationHelper.createNotificationChannel(this)

        val fcmMessagingServiceIntent = Intent(this, FCMMessagingService::class.java)
        this.startService(fcmMessagingServiceIntent)

        generateFCMToken()
    }

    private fun generateFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("TAG", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            val token = task.result
            Log.d("TAG", "FCM registration token: $token")
        }
    }
}
