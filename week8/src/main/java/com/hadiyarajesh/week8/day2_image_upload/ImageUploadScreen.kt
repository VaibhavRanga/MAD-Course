package com.hadiyarajesh.week8.day2_image_upload

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageUploadScreen(
    viewModel: ImageUploadViewModel = hiltViewModel()
) {
    val imageUploadResponse by viewModel.imageUploadResponse.collectAsState()
    var shouldUploadImageNormally = remember { true }
    val uriHandler = LocalUriHandler.current

    val imagePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            uri?.let {
                Log.d("TAG", "ImageUploadScreen uri: $it")
                viewModel.uploadImage(it.toString(), shouldUploadImageNormally)
            }
        }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Image Upload Screen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Image Upload Screen")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    shouldUploadImageNormally = true
                    imagePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            ) {
                Text(text = "Upload Image Normally")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    shouldUploadImageNormally = false
                    imagePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            ) {
                Text(text = "Upload Image via Background Worker")
            }

            Spacer(modifier = Modifier.height(16.dp))

            ImageUploadViewState(
                modifier = Modifier.fillMaxWidth(),
                state = imageUploadResponse,
                onImageUrlClick = { url ->
                    uriHandler.openUri(url)
                }
            )
        }
    }
}

@Composable
private fun ImageUploadViewState(
    modifier: Modifier = Modifier,
    state: ImageUploadViewState,
    onImageUrlClick: (String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            ImageUploadViewState.Initial -> {
            }

            ImageUploadViewState.Loading -> {
                Text(text = "Image upload is in progress...")
            }

            is ImageUploadViewState.Success -> {
                val imageUrl = state.imageUrl
                Log.d("TAG", "ImageUploadScreen imageUrl: $imageUrl")

                Row {
                    Text(
                        text = "Image URL:",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                    )

                    Text(
                        modifier = Modifier.clickable { onImageUrlClick(imageUrl) },
                        text = imageUrl,
                        style = MaterialTheme.typography.titleSmall,
                        textDecoration = TextDecoration.Underline,
                        color = Color.Blue
                    )
                }
            }

            is ImageUploadViewState.Error -> {
                Text(text = "Error: ${state.message}")
            }
        }
    }
}
