package com.hadiyarajesh.week3.day3_compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun LazyRowExample() {
    val stories: List<Media> = remember { MediaGenerator.generateMedia(MediaType.Story, 100) }

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = stories,
            key = { story -> story.id }
        ) { story ->
            StoryItem(
                story = story as Story
            )
        }
    }
}

@Composable
fun StoryItem(
    modifier: Modifier = Modifier,
    story: Story
) {
    val borderBrush = Brush.linearGradient(story.borderColors)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(2.dp, borderBrush, CircleShape)
//            .border(2.dp, Color.Red, CircleShape)
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = story.thumbnail,
                    contentDescription = story.username
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp))
        Text(story.username, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
private fun StoryItemPreview() {
    StoryItem(
        story = MediaGenerator.generateMedia(
            type = MediaType.Story,
            count = 1
        ).first() as Story
    )
}

@Preview
@Composable
private fun LazyRowPreview() {
    LazyRowExample()
}
