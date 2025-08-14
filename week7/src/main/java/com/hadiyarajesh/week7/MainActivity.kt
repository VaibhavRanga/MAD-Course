package com.hadiyarajesh.week7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hadiyarajesh.week7.day3_notifications.NotificationHelper
import com.hadiyarajesh.week7.day3_notifications.NotificationScreen
import com.hadiyarajesh.week7.ui.theme.MAD_S10Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MAD_S10Theme {
//                PaginationScreen()
                NotificationScreen()
            }
        }

        NotificationHelper.createNotificationChannel(this)
    }
}
