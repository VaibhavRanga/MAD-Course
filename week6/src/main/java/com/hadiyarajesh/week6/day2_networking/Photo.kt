package com.hadiyarajesh.week6.day2_networking

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
