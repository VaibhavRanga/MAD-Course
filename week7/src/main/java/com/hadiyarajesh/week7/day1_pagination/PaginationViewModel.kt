package com.hadiyarajesh.week7.day1_pagination

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaginationViewModel @Inject constructor(
    private val repository: PaginationRepository
) : ViewModel() {
    private val _repositories = MutableStateFlow<PagingData<Repository>>(PagingData.empty())
    val repositories: StateFlow<PagingData<Repository>> get() = _repositories

    init {
        searchRepositories(query = "kotlin")
        viewModelScope.launch {
            repositories.collect {
                it.map { Log.d("TAG", it.name) }
            }
        }
    }

    fun searchRepositories(query: String) {
        viewModelScope.launch {
            repository.searchRepositories(query).collect {
                _repositories.value = it
            }
        }
    }
}
