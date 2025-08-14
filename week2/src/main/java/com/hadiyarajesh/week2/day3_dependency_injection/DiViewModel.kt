package com.hadiyarajesh.week2.day3_dependency_injection

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiViewModel @Inject constructor(
    private val repository: DiRepository
) : ViewModel() {
//    @Inject
//    lateinit var repository: DiRepository

    fun getData(): String {
        return repository.getData()
    }
}
