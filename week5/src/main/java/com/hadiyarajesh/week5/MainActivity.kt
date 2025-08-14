package com.hadiyarajesh.week5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.hadiyarajesh.week5.day1_datastore.SettingsViewModel
import com.hadiyarajesh.week5.day2_room_database.DatabaseScreen
import com.hadiyarajesh.week5.ui.theme.MAD_S10Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAD_S10Theme {
//                val isUserAuthenticated by settingsViewModel.isUserAuthenticated.collectAsState()
//
//                if (isUserAuthenticated) {
//                    HomeScreen()
//                } else {
//                    LoginScreen()
//                }

                DatabaseScreen()
            }
        }
    }
}
