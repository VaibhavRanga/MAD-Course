package com.hadiyarajesh.week3.day2_compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposableHoistingChild(
    modifier: Modifier = Modifier,
    counter: Int,
    onCounterClick: () -> Unit,
    buttonComponent: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .border(1.dp, Color.Red, RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Click counter: $counter")

        buttonComponent()
//        OutlinedButton(
//        Button(
//            onClick = { onCounterClick() }
//        ) {
//            Text("Increment counter")
//        }
    }
}

@Composable
fun ComposableHoistingParent() {
    var counter: Int by remember { mutableIntStateOf(0) }

    ComposableHoistingChild(
        modifier = Modifier.fillMaxSize(),
        counter = counter,
        onCounterClick = { counter++ },
        buttonComponent = {
//            OutlinedButton(
            Button(
                onClick = { counter++ }
            ) {
                Text("Increment counter")
            }
        }
    )
}

@Preview
@Composable
private fun ComposableHoistingParentPreview() {
    ComposableHoistingParent()
}