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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    HomeScreenContent(
        onDetailClick = {
            navController.navigate(NavDestination.Detail.route + "/Rajesh")
        },
        onViewModelScreenClick = {
            navController.navigate(NavDestination.ViewModelScreen.route)
        },
        onTypeSafeDestinationClick = {
            navController.navigate(NavDestination.TypeSafeDestination("Rajesh", "Hadiya"))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    onDetailClick: () -> Unit,
    onViewModelScreenClick: () -> Unit,
    onTypeSafeDestinationClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Home") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home Screen")

            Button(
                onClick = {
                    onDetailClick()
//                    navController.navigate(NavDestination.Detail.route + "/Rajesh")
//                    navController.navigate(NavDestination.Detail.withArgs("Rajesh", "Hadiya"))
//                    navController.navigate(NavDestination.Detail.route + "/Rajesh?surname=Hadiya")
                }
            ) {
                Text("Go to Detail Screen")
            }

            Button(onClick = onViewModelScreenClick) {
                Text("Go to ViewModel Screen")
            }

            Button(onClick = onTypeSafeDestinationClick) {
                Text("Go to Type Safe Destination Screen")
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent({}, {}, {})
}
