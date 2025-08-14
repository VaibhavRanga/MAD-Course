package com.hadiyarajesh.week3.day2_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StateHoistingChild(
    count: Int,
//    onCountChange: (Int) -> Unit,
    onCountChange: () -> Unit,
) {
//    var count: Int by remember { mutableStateOf<Int>(0) }

    Column(
//        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Click count: $count")

        Button(
            onClick = { onCountChange() }
        ) {
            Text("Increment counter")
        }
    }
}

@Composable
fun StateHoistingParent() {
    var count: Int by remember { mutableStateOf<Int>(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StateHoistingChild(count, { count = count + 2 })
        StateHoistingChild(1, {})
    }
}

@Preview
@Composable
private fun StateHoistingParentPreview() {
    StateHoistingParent()
}
