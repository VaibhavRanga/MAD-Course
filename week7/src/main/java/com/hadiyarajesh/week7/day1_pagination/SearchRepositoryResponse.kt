package com.hadiyarajesh.week7.day1_pagination

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchRepositoryResponse(
    @Json(name = "total_count")
    val totalCount: Long,
    val items: List<Repository>
)

@JsonClass(generateAdapter = true)
data class Repository(
    val id: Long,
    val name: String,
    val owner: RepositoryOwner,
    val description: String?,
    @Json(name = "html_url")
    val repositoryUrl: String,
    @Json(name = "stargazers_count")
    val numberOfStars: Long
)

@JsonClass(generateAdapter = true)
data class RepositoryOwner(
    val id: Long,
    @Json(name = "login")
    val username: String,
    @Json(name = "avatar_url")
    val profilePicUrl: String,
    @Json(name = "html_url")
    val profileUrl: String
)
