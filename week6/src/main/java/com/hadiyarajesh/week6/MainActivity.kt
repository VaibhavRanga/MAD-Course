package com.hadiyarajesh.week6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hadiyarajesh.week6.day2_networking.NetworkingScreen
import com.hadiyarajesh.week6.ui.theme.MAD_S10Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MAD_S10Theme {
//                FileStorageScreen()
//                ImageViewerScreen()
                NetworkingScreen()
            }
        }
    }
}
