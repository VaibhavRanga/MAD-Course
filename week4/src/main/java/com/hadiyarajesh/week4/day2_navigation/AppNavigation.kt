package com.hadiyarajesh.week4.day2_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavDestination.Home.route
    ) {
        composable(route = NavDestination.Home.route) {
            HomeScreen(navController)
        }

        composable(
            route = NavDestination.Detail.route + "/{name}?surname={surname}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("surname") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: return@composable
            val surname = backStackEntry.arguments?.getString("surname")

            DetailScreen(navController, name, surname)
        }

        composable(
            route = NavDestination.SwapnaScreen.route
        ) {
            SwapnaScreen(navController)
        }

        composable(
            route = NavDestination.ViewModelScreen.route
        ) {
            ViewModelScreen()
        }

        composable<NavDestination.TypeSafeDestination> { backStackEntry ->
//            val name = backStackEntry.arguments?.getString("name") ?: return@composable
//            val surname = backStackEntry.arguments?.getString("surname") ?: return@composable
//            val destination = NavDestination.TypeSafeDestination(name, surname)

            val destination =
                backStackEntry.savedStateHandle.toRoute<NavDestination.TypeSafeDestination>()
            TypeSafeDestinationScreen(navController, destination)
        }
    }
}
