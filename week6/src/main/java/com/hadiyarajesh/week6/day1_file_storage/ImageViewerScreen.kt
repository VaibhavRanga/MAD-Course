package com.hadiyarajesh.week6.day1_file_storage

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalPermissionsApi::class
)
@Composable
fun ImageViewerScreen() {
    val context = LocalContext.current
    val images = remember { ImageViewerHelper.fetchAllImagesFromStorage(context) }
    val readImagesPermission = rememberPermissionState("android.permission.READ_MEDIA_IMAGES")

    LaunchedEffect(Unit) {
        if (readImagesPermission.status != PermissionStatus.Granted) {
            readImagesPermission.launchPermissionRequest()
        }
    }

    LaunchedEffect(images) {
        Log.d("TAG", "images: $images")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "ImageViewerScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImagesGrid(
                modifier = Modifier.fillMaxSize(),
                images = images
            )
        }
    }
}

@Composable
private fun ImagesGrid(
    modifier: Modifier = Modifier,
    images: List<Image>
) {
    val minImageSize = 150.dp
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minImageSize),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(images) { image ->
            ImageItem(
                modifier = Modifier
                    .size(minImageSize)
                    .clip(RoundedCornerShape(8.dp)),
                image = image
            )
        }
    }
}

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    image: Image
) {
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = image.uri,
            contentDescription = image.name,
            contentScale = ContentScale.Crop
        )
    }
}

data class Image(
    val id: Long,
    val name: String,
    val uri: Uri
)

object ImageViewerHelper {
    fun fetchAllImagesFromStorage(
        context: Context
    ): List<Image> {
        val images = mutableListOf<Image>()

        val uri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME)
        val sortOrder = "${MediaStore.Images.Media.DISPLAY_NAME} ASC"

        val cursor = context.contentResolver.query(uri, projection, null, null, sortOrder)

        cursor?.let {
            val idColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumnIndex)
                val name = cursor.getString(nameColumnIndex)
                val uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
                )

                val image = Image(id, name, uri)
                images.add(image)
            }

            cursor.close()
        }

        return images
    }
}
