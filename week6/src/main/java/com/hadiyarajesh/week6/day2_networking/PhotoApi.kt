package com.hadiyarajesh.week6.day2_networking

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PhotoApi {
    @GET("photos")
    suspend fun getAllPhotos(): List<Photo>

    @GET("photos")
    fun getAllPhotosCall(): Call<List<Photo>>

    @GET("photos")
    suspend fun getAllPhotosResponse(): Response<List<Photo>>

    @POST("photos")
    suspend fun createPhoto(@Body photo: Photo): Photo
}
