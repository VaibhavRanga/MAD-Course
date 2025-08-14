package com.hadiyarajesh.week4.day2_navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwapnaScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Swapna Screen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Swapna Screen")

            Button(
                onClick = {
//                    navController.navigate(NavDestination.Home.route) {
//                        popUpTo(NavDestination.Home.route) {
//                            inclusive = true
//                        }
//                    }
                    navController.popBackStack(NavDestination.Home.route, inclusive = false)
                }
            ) {
                Text(text = "Go to Home Screen")
            }
        }
    }
}