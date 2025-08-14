package com.hadiyarajesh.week8.day2_image_upload

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MultipartBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject

interface ImageUploadRepository {
    suspend fun uploadImage(imageUriString: String): ImageUploadResponse
}

class ImageUploadRepositoryImpl @Inject constructor(
    private val apiService: ImageUploadApi,
    @ApplicationContext private val context: Context
) : ImageUploadRepository {
    override suspend fun uploadImage(imageUriString: String): ImageUploadResponse {
        val imageUriString = context.getBase64StringFromUri(imageUriString.toUri())
            ?: throw IllegalArgumentException("Invalid image URI")

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", imageUriString)
            .addFormDataPart("format", "json")
            .build()

        return apiService.uploadImage(body = requestBody)
    }
}

internal fun Context.getBase64StringFromUri(uri: Uri): String? {
    val inputStream = this.contentResolver.openInputStream(uri)
    val imageBitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)

    return imageBitmap?.let { bitmap ->
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        inputStream?.close()
        Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}
