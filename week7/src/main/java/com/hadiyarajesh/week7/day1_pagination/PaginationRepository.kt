package com.hadiyarajesh.week7.day1_pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PaginationRepository {
    suspend fun searchRepositories(query: String): Flow<PagingData<Repository>>
}

class PaginationRepositoryImpl @Inject constructor(
    private val api: GitHubSearchApi
) : PaginationRepository {
//    override suspend fun searchRepositories(query: String): List<Repository> {
//        return api.searchRepositories(query).items
//    }

    override suspend fun searchRepositories(query: String): Flow<PagingData<Repository>> {
        val pageSize = 10
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                initialLoadSize = 4 * pageSize,
                prefetchDistance = 10
            )
        ) {
            RepositoryPagingSource(searchQuery = query, api = api)
        }.flow
    }
}
