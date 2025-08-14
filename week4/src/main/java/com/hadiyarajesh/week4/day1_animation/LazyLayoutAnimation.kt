package com.hadiyarajesh.week4.day1_animation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val originalList = (1..10).map { "Item $it" }

@Composable
fun LazyLayoutAnimation() {
    var list = remember { mutableStateListOf<String>(*originalList.toTypedArray()) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(
                items = list,
                key = { item -> item },
            ) { item ->
                LazyListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(placementSpec = tween(500)),
                    item = item,
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
//                    val randomItemIndex = Random.nextInt(0, list.lastIndex)
//                    list.removeAt(randomItemIndex)
                    list.shuffle()
                }
            ) {
                Text("Remove item")
            }
        }
    }
}

@Composable
fun LazyListItem(
    modifier: Modifier = Modifier,
    item: String
) {
    Row(
        modifier = modifier
            .border(1.dp, Color.Blue, RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


@Preview
@Composable
private fun LazyLayoutAnimationPreview() {
    LazyLayoutAnimation()
}
