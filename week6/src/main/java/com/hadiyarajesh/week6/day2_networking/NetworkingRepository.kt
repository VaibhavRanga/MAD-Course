package com.hadiyarajesh.week6.day2_networking

import javax.inject.Inject

interface NetworkingRepository {
    suspend fun getAllPhotos(): List<Photo>

    fun getAllPhotosCall(
        onSuccess: (List<Photo>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    suspend fun getAllPhotosResponse(
        onSuccess: (List<Photo>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    suspend fun createPhoto(photo: Photo): Photo
}

class NetworkingRepositoryImpl @Inject constructor(
    private val photoApi: PhotoApi
) : NetworkingRepository {
    override suspend fun getAllPhotos(): List<Photo> {
        return photoApi.getAllPhotos()
    }

    override fun getAllPhotosCall(
        onSuccess: (List<Photo>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        try {
            val photos = photoApi.getAllPhotosCall().execute().body()
            if (photos != null) {
                onSuccess(photos)
            }
        } catch (e: Exception) {
            onFailure(e)
        }

//        photoApi.getAllPhotosCall().enqueue(object : Callback<List<Photo>> {
//            override fun onResponse(
//                call: Call<List<Photo>?>,
//                response: Response<List<Photo>?>
//            ) {
//                response.body()?.let { onSuccess(it) }
//            }
//
//            override fun onFailure(
//                call: Call<List<Photo>?>,
//                t: Throwable
//            ) {
//                onFailure(t)
//            }
//        }
//        )
    }

    override suspend fun getAllPhotosResponse(
        onSuccess: (List<Photo>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        try {
            val response = photoApi.getAllPhotosResponse()

            val photos = response.body()
            if (photos != null) {
                onSuccess(photos)
            }
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    override suspend fun createPhoto(photo: Photo): Photo {
        return photoApi.createPhoto(photo)
    }
}
