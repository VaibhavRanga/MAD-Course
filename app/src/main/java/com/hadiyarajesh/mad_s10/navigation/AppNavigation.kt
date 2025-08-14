package com.hadiyarajesh.mad_s10.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hadiyarajesh.mad_s10.ui.components.slideIntoContainerAnimation
import com.hadiyarajesh.mad_s10.ui.components.slideOutOfContainerAnimation
import com.hadiyarajesh.mad_s10.ui.detail.DetailRoute
import com.hadiyarajesh.mad_s10.ui.home.HomeRoute
import com.hadiyarajesh.mad_s10.utility.Constants

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = TopLevelDestination.Home.route
    ) {
        composable(
            route = TopLevelDestination.Home.route,
            enterTransition = { slideIntoContainerAnimation() },
            exitTransition = { slideOutOfContainerAnimation() }
        ) {
            HomeRoute(
                onNavigateClick = { source ->
                    navController.navigate(TopLevelDestination.Detail.withArgs(source))
                }
            )
        }

        composable(
            route = TopLevelDestination.Detail.route + "/{${Constants.SOURCE}}",
            arguments = listOf(
                navArgument(Constants.SOURCE) {
                    type = NavType.StringType
                }
            ),
            enterTransition = {
                slideIntoContainerAnimation(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                slideOutOfContainerAnimation(
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) { backStackEntry ->
            val source = backStackEntry.arguments?.getString(Constants.SOURCE) ?: return@composable
            DetailRoute(
                source = source,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
