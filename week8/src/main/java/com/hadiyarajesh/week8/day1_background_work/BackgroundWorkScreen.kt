package com.hadiyarajesh.week8.day1_background_work

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackgroundWorkScreen(
    viewModel: BackgroundWorkViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Background Work Screen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "This is the Background Work Screen.")

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.scheduleOneTimeWorkRequest() }
            ) {
                Text(text = "Schedule one time work request")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.scheduleOneTimeWorkRequestWithData() }
            ) {
                Text(text = "Schedule one time work request with Data")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.scheduleOneTimeWorkRequestWithConstraints() }
            ) {
                Text(text = "Schedule one time work request with Constraints")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.schedulePeriodicWorkRequest() }
            ) {
                Text(text = "Schedule periodic work request")
            }
        }
    }
}
