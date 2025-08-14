package com.hadiyarajesh.week9.day1_app_localization

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hadiyarajesh.week9.day1_app_localization.settings.SettingsScreen

@Composable
fun MultiLanguageAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route
    ) {
        composable(
            route = Destination.Home.route
        ) {
            AppLocalizationScreen(
                onSettingsClick = {
                    navController.navigate(Destination.Settings.route)
                }
            )
        }

        composable(
            route = Destination.Settings.route
        ) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}