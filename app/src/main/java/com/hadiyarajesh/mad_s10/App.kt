package com.hadiyarajesh.mad_s10

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hadiyarajesh.mad_s10.navigation.AppNavigation
import com.hadiyarajesh.mad_s10.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()

        Scaffold { innerPadding ->
            AppNavigation(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}
