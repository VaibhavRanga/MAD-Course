package com.hadiyarajesh.week10.day3_memory_leak

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryLeakScreen(
    viewModel: MemoryLeakViewModel
) {
    val resourceString by viewModel.resourceString.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "MemoryLeakScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Memory Leak Screen")

            AnimatedVisibility(visible = resourceString != null) {
                Text(text = resourceString!!)
            }
        }
    }
}
