package com.hadiyarajesh.week7.day1_pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class RepositoryPagingSource @Inject constructor(
    private val searchQuery: String,
    private val api: GitHubSearchApi
) : PagingSource<Int, Repository>() {
    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val currentPageNumber = params.key ?: 1

        return try {
            val response = api.searchRepositories(
                searchQuery = searchQuery,
                pageNumber = currentPageNumber,
                pageSize = params.loadSize
            )

            val repositories = response.items

            LoadResult.Page(
                data = repositories,
                prevKey = if (currentPageNumber == 1) null else currentPageNumber - 1,
                nextKey = if (repositories.isEmpty()) null else currentPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}