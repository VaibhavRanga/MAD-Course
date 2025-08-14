package com.hadiyarajesh.week7.day1_pagination

import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubSearchApi {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") pageSize: Int
    ): SearchRepositoryResponse
}
