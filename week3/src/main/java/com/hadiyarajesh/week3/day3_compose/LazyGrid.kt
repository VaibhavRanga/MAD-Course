package com.hadiyarajesh.week3.day3_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun LazyGrid() {
    val posts: List<Media> = remember { MediaGenerator.generateMedia(MediaType.Post, 100) }
    val minPostSize = 100.dp

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
//        columns = GridCells.Fixed(3),
        columns = GridCells.Adaptive(minPostSize),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Header",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
        items(posts) {
            PostItem(
                modifier = Modifier
                    .size(minPostSize)
                    .clip(RoundedCornerShape(8.dp)),
                story = it as Post
            )
        }
    }
}

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    story: Post
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = story.thumbnail,
            contentDescription = story.username,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
private fun LazyGridPreview() {
    LazyGrid()
}
