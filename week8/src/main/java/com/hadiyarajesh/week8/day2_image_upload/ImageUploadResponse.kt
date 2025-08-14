package com.hadiyarajesh.week8.day2_image_upload

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageUploadResponse(
    @Json(name = "status_code")
    val statusCode: Int,
    val image: Image
)

@JsonClass(generateAdapter = true)
data class Image(
    val name: String,
    val extension: String,
    val url: String
)
