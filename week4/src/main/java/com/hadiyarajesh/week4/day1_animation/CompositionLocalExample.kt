package com.hadiyarajesh.week4.day1_animation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CompositionLocalExample() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CompositionLocalProvider(
            LocalContentColor provides Color.Blue,
            LocalTextStyle provides MaterialTheme.typography.headlineMedium
        ) {
            Text(
                text = "Hey, Rajesh",
//                style = MaterialTheme.typography.headlineLarge.copy(
//                    fontWeight = FontWeight.SemiBold
//                )
//            color = MaterialTheme.colorScheme.error,
            )
        }
    }
}

@Preview
@Composable
private fun CompositionLocalExamplePreview() {
    CompositionLocalExample()
}

data class MyColor(val color: Color)

val LocalMyColor = compositionLocalOf { MyColor(Color.Red) }

@Composable
fun CustomCompositionLocalExample() {
    val state by remember { mutableStateOf(MyColor(Color.Blue)) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hey, Rajesh",
            style = MaterialTheme.typography.headlineLarge,
            color = LocalMyColor.current.color
        )

        CompositionLocalProvider(
            LocalMyColor provides state
        ) {
            Text(
                text = "Welcome, Rajesh",
                style = MaterialTheme.typography.headlineLarge,
                color = LocalMyColor.current.color
            )
        }
    }
}

@Preview
@Composable
private fun CustomCompositionLocalExamplePreview() {
    CompositionLocalProvider(
        LocalMyColor provides MyColor(Color.Red)
    ) {
        CustomCompositionLocalExample()
    }
}
