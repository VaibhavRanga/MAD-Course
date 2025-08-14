package com.hadiyarajesh.week3.day2_compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hadiyarajesh.week3.R
import kotlin.random.Random

@Composable
fun LazyColumnExample() {
    val originalList = (1..10).map { "Item $it" }
//    val list: List<String> = (1..100).map { "Item $it " }
//    val list: MutableList<String> = (1..10).map { "Item $it " }.toMutableList()
//    val list: MutableList<String> by remember { mutableStateOf((1..10).map { "Item $it" }.toMutableList()) }
//    var list: List<String> by remember { mutableStateOf((1..10).map { "Item $it" }) }
    //        .toMutableList().apply { add(0, "Header") }
    var list = remember { mutableStateListOf<String>(*originalList.toTypedArray()) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            item {
                LazyListItem(
                    modifier = Modifier.fillMaxWidth(),
                    item = "Header",
                    isHeader = true
                )
            }

//        list.forEach { item ->
//            item {
//                LazyListItem(
//                    modifier = Modifier.fillMaxWidth(),
//                    item = item,
//                    isHeader = true
//                )
//            }
//        }

            items(
                items = list,
                key = { item -> item },
                contentType = { item -> item }
            ) { item -> // "Item 1", "Item 2"
//                if(user.hasPhoto) { showPhoto() } else { showVideo() }

                LazyListItem(
                    modifier = Modifier.fillMaxWidth(),
                    item = item,
                    isHeader = false
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
//            modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    val randomItemIndex = Random.nextInt(0, list.lastIndex)
//                    Log.d("TAG", "randomItemIndex: $randomItemIndex")
                    list.removeAt(randomItemIndex)
//                    val tempList = list.toMutableList()
//                    tempList.removeAt(randomItemIndex)
//                    list = tempList.toList()
//                    Log.d("TAG", "is same?: ${tempList == list}")
                }
            ) {
                Text("Remove item")
            }
        }
    }
}


enum class MediaType {
    Photo,
    Video;
}

data class User(
    val id: Long,
    val type: MediaType
)

@Composable
fun LazyListItem(
    modifier: Modifier = Modifier,
    item: String,
    isHeader: Boolean
) {
    Log.d("TAG", "Printing $item")

    Row(
        modifier = modifier
            .border(1.dp, if (isHeader) Color.Blue else Color.Red, RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Text(
//            modifier = Modifier.weight(1f),
//            text = item,
//            style = MaterialTheme.typography.headlineMedium,
//            textAlign = TextAlign.Center
//        )

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun LazyColumnExamplePreview() {
    LazyColumnExample()
}