package com.hadiyarajesh.week4.day1_animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedVisibilityExample() {
    var showBox by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = showBox,
            enter = fadeIn() + expandVertically(
                animationSpec = tween(2000),
                expandFrom = Alignment.Bottom
            ),
            exit = fadeOut() + shrinkVertically(
                animationSpec = tween(2000),
                shrinkTowards = Alignment.Bottom
            )
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Green)
                    .border(1.dp, Color.Blue, RoundedCornerShape(8.dp))
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {
                showBox = !showBox
            }
        ) {
            Text("Toggle box visibility")
        }
    }
}

@Composable
fun AnimatedContentMeraWala(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn() + expandVertically(
            animationSpec = tween(2000),
            expandFrom = Alignment.Bottom
        ),
        exit = fadeOut() + shrinkVertically(
            animationSpec = tween(2000),
            shrinkTowards = Alignment.Bottom
        )
    ) {
        content()
    }
}

@Preview
@Composable
private fun AnimatedVisibilityExamplePreview() {
    AnimatedVisibilityExample()
}
