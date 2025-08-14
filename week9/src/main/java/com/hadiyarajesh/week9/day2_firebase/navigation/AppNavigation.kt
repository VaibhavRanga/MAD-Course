package com.hadiyarajesh.week9.day2_firebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hadiyarajesh.week9.day2_firebase.authentication.SignInScreen
import com.hadiyarajesh.week9.day2_firebase.authentication.SignUpScreen
import com.hadiyarajesh.week9.day2_firebase.home.HomeScreen

@Composable
fun FirebaseAppNavigation(
    startDestination: NavDestination
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(
            route = NavDestination.Home.route
        ) {
            HomeScreen()
        }

        composable(
            route = NavDestination.SignIn.route
        ) {
            SignInScreen(
                onSignUpClick = { navController.navigate(NavDestination.SignUp.route) }
            )
        }

        composable(
            route = NavDestination.SignUp.route
        ) {
            SignUpScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}