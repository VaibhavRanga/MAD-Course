package com.hadiyarajesh.week8.day2_image_upload

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ImageUploadWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParams: WorkerParameters,
    val repository: ImageUploadRepository
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val imageUriString = inputData.getString("imageUri")
            ?: throw IllegalArgumentException("Invalid input")

        // Update progress of work if required.
//        repeat(5) {
//            delay(1.seconds)
//            val progress = Data.Builder().putInt("progress", it).build()
//            setProgressAsync(progress)
//        }

        val response = repository.uploadImage(imageUriString)

        Log.d("TAG", "response: $response")

        val outputData = Data.Builder()
            .putString("imageUrl", response.image.url)
            .build()

        return Result.success(outputData)
    }
}
