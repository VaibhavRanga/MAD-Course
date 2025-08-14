package com.hadiyarajesh.week10.day3_memory_leak

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.week10.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MemoryLeakViewModel(
//    @ApplicationContext
    private val context: Context,
//    private val application: Application
) : ViewModel() {
    //) : AndroidViewModel(application) {
    private val _resourceString = MutableStateFlow<String?>(null)
    val resourceString: StateFlow<String?> get() = _resourceString

    init {
        viewModelScope.launch {
            delay(3_000)
            updateResourceString()
        }
    }

    fun updateResourceString() {
        _resourceString.value = context.getString(R.string.memory_leak_description)
        _resourceString.value = "Static memory leak string"
    }
}

class MemoryLeakViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoryLeakViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MemoryLeakViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
