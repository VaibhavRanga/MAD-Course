package com.hadiyarajesh.week4.day2_navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {
    private val _data = MutableStateFlow<List<Int>>(emptyList())
    val data: StateFlow<List<Int>> get() = _data.asStateFlow()

    fun updateData() {
        viewModelScope.launch {
            val listSize = Random.nextInt(10)
            val newList = List(listSize) { Random.nextInt(0, listSize) }
            delay(2_000)
            _data.value = newList
        }
    }
}
