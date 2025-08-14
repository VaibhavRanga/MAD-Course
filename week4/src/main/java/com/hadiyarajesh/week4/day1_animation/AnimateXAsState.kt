package com.hadiyarajesh.week4.day1_animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimateColorAsState() {
    var showBigBox by remember { mutableStateOf(false) }
    val boxSize: Size by animateSizeAsState(
        targetValue = if (showBigBox) Size(200) else Size(100),
        animationSpec = tween(2000)
    )

    //    var boxColor by remember { mutableStateOf(Color.Blue) }
    var showBlueColor by remember { mutableStateOf(false) }
    val boxColor: Color by animateColorAsState(
        targetValue = if (showBlueColor) Color.Blue else Color.Green,
        animationSpec = tween(2000)
    )

    var currentProgressStep by remember { mutableFloatStateOf(0f) }
    val animatedProgress by animateFloatAsState(
        targetValue = currentProgressStep,
        animationSpec = tween(durationMillis = 500), // Animation duration
        label = "progressAnimation"
    )

    var runAnimation by remember { mutableStateOf(false) }
    var isRestartEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(runAnimation) {
        isRestartEnabled = false
        repeat(5) { it ->
            currentProgressStep = 0.2f * (it + 1)
            delay(1000)
        }
        isRestartEnabled = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(boxSize.packedValue.toInt().dp)
                .clip(RoundedCornerShape(8.dp))
                .background(boxColor)
                .border(1.dp, Color.Blue, RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {
                showBlueColor = !showBlueColor
//                if (boxColor == Color.Blue) {
//                    boxColor = Color.Green
//                } else {
//                    boxColor = Color.Blue
//                }
            }
        ) {
            Text("Toggle box color")
        }

        Button(
            onClick = {
                showBigBox = !showBigBox
            }
        ) {
            Text("Toggle box size")
        }

        Spacer(modifier = Modifier.size(32.dp))

        LinearProgressIndicator(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            progress = { animatedProgress }
        )

        Button(
            enabled = isRestartEnabled,
            onClick = {
                runAnimation = !runAnimation
            }
        ) {
            Text("Restart Progress Animation")
        }


//        Button(
//            onClick = {
//                // Increment progress by 0.2, and loop back to 0 if it exceeds 1.0
//                val nextProgress = currentProgressStep + 0.2f
//                // Ensure progress doesn't go slightly above 1.0 due to floating point inaccuracies
//                // and reset to 0 if it's already at or passed 1.0.
//                currentProgressStep =
//                    if (currentProgressStep >= 1.0f) 0f else (currentProgressStep + 0.2f).coerceIn(
//                        0f,
//                        1f
//                    )
//            }
//        ) {
//            Text("Update Progress")
//        }
    }
}

@Preview
@Composable
private fun AnimateColorAsStatePreview() {
    AnimateColorAsState()
}