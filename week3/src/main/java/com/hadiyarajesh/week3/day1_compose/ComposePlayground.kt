package com.hadiyarajesh.week3.day1_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyFirstComposable() {
    Button(onClick = {}) {
        Text(text = "Button")
    }

    Text(text = "My TextView")
}

@Composable
fun ColumnExample() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Text(text = "My TextView")

        Button(onClick = {}) {
            Text(text = "Button")
        }
    }
}

@Composable
fun RowExample() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "My TextView")

        Button(onClick = {}) {
            Text(text = "Button")
        }
    }
}

@Composable
fun BoxExample() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {}) {
            Text(text = "Button")
        }

        Text(text = "My TextView")
    }
}

@Composable
fun ModifierExample() {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(300.dp)
//            .size(100.dp)
            .background(Color.Blue)
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
                .padding(8.dp)
                .border(4.dp, Color.Red)
                .padding(10.dp)
                .border(1.dp, Color.Blue)
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .size(50.dp)
//                    .clip(RoundedCornerShape(10.dp))
                    .clip(CircleShape)
                    .background(Color.Magenta)
                    .align(Alignment.TopCenter)
            )

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Rajesh"
            )

            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {}) {
                Text(text = "Button")
            }
        }
    }
}

//@Preview
//@Composable
//fun MyFirstComposablePreview() {
//    MyFirstComposable()
//}

//@Preview(showSystemUi = true)
//@Composable
//private fun ColumnExamplePreview() {
//    ColumnExample()
//}

//@Preview(showSystemUi = true)
//@Composable
//private fun RowExamplePreview() {
//    RowExample()
//}

//@Preview
//@Composable
//private fun BoxExamplePreview() {
//    BoxExample()
//}

@Preview
@Composable
private fun ModifierExamplePreview() {
    ModifierExample()
}