package com.hadiyarajesh.week8.day2_image_upload

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ImageUploadApi {
    @POST("upload")
    suspend fun uploadImage(
        @Query("key") key: String = "6d207e02198a847aa98d0a2a901485a5",
        @Body body: RequestBody
    ): ImageUploadResponse
}
