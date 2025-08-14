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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewModelScreen(
    viewModel: MyViewModel = hiltViewModel()
) {
//    val data by viewModel.data.collectAsState() // Not lifecycle-aware
    val data by viewModel.data.collectAsStateWithLifecycle() // lifecycle-aware

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "ViewModelScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            data.forEach {
                Text("Item $it")
            }

            Button(onClick = { viewModel.updateData() }) {
                Text(text = "Update Data")
            }
        }
    }
}
