package com.hadiyarajesh.week8.day2_image_upload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageUploadViewModel @Inject constructor(
    private val repository: ImageUploadRepository,
//    private val workManager: WorkManager
    private val workManagerWrapper: WorkManagerWrapper
) : ViewModel() {
    private val _imageUploadResponse =
        MutableStateFlow<ImageUploadViewState>(ImageUploadViewState.Initial)
    val imageUploadResponse: StateFlow<ImageUploadViewState> get() = _imageUploadResponse.asStateFlow()

    fun uploadImage(imageUri: String, shouldUploadImageNormally: Boolean) {
//        if (shouldUploadImageNormally) {
//            uploadImageNormally(imageUri)
//        } else {
//            uploadImageViaBackgroundWorker(imageUri)
//        }

        val functionReference =
            if (shouldUploadImageNormally) ::uploadImageNormally else ::uploadImageViaBackgroundWorker
        functionReference(imageUri)
    }

    private fun uploadImageNormally(imageUri: String) {
        viewModelScope.launch {
            _imageUploadResponse.value = ImageUploadViewState.Loading

            try {
                val response = repository.uploadImage(imageUri)
                _imageUploadResponse.value = ImageUploadViewState.Success(response.image.url)
            } catch (e: Exception) {
                _imageUploadResponse.value =
                    ImageUploadViewState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun uploadImageViaBackgroundWorker(imageUri: String) {
        val inputData = Data.Builder()
            .putString("imageUri", imageUri)
            .build()

        val workRequest = OneTimeWorkRequest.Builder(ImageUploadWorker::class.java)
            .setInputData(inputData)
//            .setInitialDelay(5, TimeUnit.SECONDS)
            .build()

        workManagerWrapper.enqueue(workRequest)

        viewModelScope.launch(Dispatchers.IO) {
            workManagerWrapper.getWorkInfoByIdFlow(workRequest.id).collect { workInfo ->
                workInfo?.let {
                    val progress = it.progress.getInt("progress", -1)
//                    Log.d("TAG", "Progress: $progress")

                    when (workInfo.state) {
                        WorkInfo.State.RUNNING -> {
                            _imageUploadResponse.value = ImageUploadViewState.Loading
                        }

                        WorkInfo.State.SUCCEEDED -> {
                            val imageUrl = workInfo.outputData.getString("imageUrl")
                            if (imageUrl != null) {
                                _imageUploadResponse.value = ImageUploadViewState.Success(imageUrl)
                            }
                        }

                        WorkInfo.State.FAILED -> {
                            _imageUploadResponse.value =
                                ImageUploadViewState.Error("Background work failed.")
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}

sealed interface ImageUploadViewState {
    object Initial : ImageUploadViewState
    object Loading : ImageUploadViewState
    data class Success(val imageUrl: String) : ImageUploadViewState
    data class Error(val message: String) : ImageUploadViewState
}