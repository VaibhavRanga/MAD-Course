package com.hadiyarajesh.week3.day1_compose

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

//var count: MutableState<Int> = mutableStateOf<Int>(0)

@Composable
fun RecompositionExample() {
//    var count = 0
//    var count: MutableState<Int> = remember { mutableStateOf<Int>(0) }
    var count: Int by remember { mutableStateOf<Int>(0) }
//    var count: Int by rememberSaveable { mutableStateOf<Int>(0) }
//    var text: String by remember { mutableStateOf("") }
    val (text, onTextChange) = remember { mutableStateOf("") }

//    SideEffect {
//        Log.d("TAG", "Recomposition is happening")
//    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Click count: ${count}")

        Button(
            onClick = {
                Log.d("TAG", "counter before: ${count}")
                count++
                Log.d("TAG", "counter after: ${count}")
            }
        ) {
            Text("Increment counter")
        }

        TextField(
            value = text,
            onValueChange = onTextChange
        )
    }
}

@Preview
@Composable
private fun RecompositionExamplePreview() {
    RecompositionExample()
}

//data class User(val id: Int, val name: String)
//
//fun main() {
////    val user = User(1, "Rajesh")
////    println(user.id)
////    println(user.name)
////
////    val (id, name) = User(1, "Rajesh")
////    println(id)
////    println(name)
//
//    val list = listOf(1, 2, 3)
//    println(list)
//
//    val (first, second, third) = list
//    println(first)
//    println(second)
//    println(third)
//}

fun main() {
    val data by lazy { "Rajesh" }
    println(data)
}