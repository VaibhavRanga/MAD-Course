package com.hadiyarajesh.week3.day2_compose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ScrollableComponent() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        repeat(100) { index ->
            ListItem(value = "Item ${index + 1}")
        }
    }
}

@Composable
fun ListItem(value: String) {
    Log.d("TAG", "Printing $value")

    Text(
        text = value,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Preview
@Composable
private fun ScrollableComponentPreview() {
    ScrollableComponent()
}
