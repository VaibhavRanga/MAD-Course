package com.hadiyarajesh.week4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hadiyarajesh.week4.day2_navigation.AppNavigation
import com.hadiyarajesh.week4.ui.theme.MAD_S10Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MAD_S10Theme {
                AppNavigation()
            }
        }
    }
}
