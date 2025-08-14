package com.hadiyarajesh.week3.day2_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.FileInputStream
import java.io.IOException

@Composable
fun SideEffectExample() {
    var counter1: Int by remember { mutableIntStateOf(0) }
    var counter2: Int by remember { mutableIntStateOf(0) }
//
//    Log.d("TAG", "Recomposition is happening outside")
//
//    SideEffect {
//        Log.d("TAG", "Recomposition is happening in SideEffect")
//    }
//
//    LaunchedEffect(counter1, counter2) {
//        delay(1000)
//        Log.d("TAG", "Counter 1 or 2 is changed")
//    }

//    DisposableEffect(counter1) {
//        Log.d("TAG", "Counter 1 at value is changed")
//
//        onDispose {
//            Log.d("TAG", "Counter 1 at value is disposed")
//        }
//    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Click counter 1: $counter1")

        Button(
            onClick = { counter1++ }
        ) {
            Text("Increment counter 1")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (counter1 % 2 == 0) {
//            DisposableEffect(counter1) {
//                Log.d("TAG", "Inside counter1")
//
//                onDispose {
//                    Log.d("TAG", "Tata bye bye khatam chalta hu")
//                }
//            }

            Text(text = "Click counter 2: $counter2")

            Button(
                onClick = { counter2++ }
            ) {
                Text("Increment counter 2")
            }
        }
    }
}

//@Composable
//fun DisposableEffectExample() {
//    var counter: Int by remember { mutableIntStateOf(0) }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = "Click counter: $counter")
//
//        Button(
//            onClick = { counter++ }
//        ) {
//            Text("Increment counter")
//        }
//    }
//}

@Preview
@Composable
private fun SideEffectExamplePreview() {
    SideEffectExample()
}

fun main() {
    val file = File("file1.txt")
    val stream = FileInputStream(file)
    try {
        stream.use {
            // read data from input stream
        }
    } catch (e: IOException) {
        println("Error: ${e.message}")
    } finally {
        stream.close()
    }
}
