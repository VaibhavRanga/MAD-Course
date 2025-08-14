package com.hadiyarajesh.week8.day1_background_work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BackgroundWorkViewModel @Inject constructor(
    private val repository: BackgroundWorkRepository
) : ViewModel() {
    fun scheduleOneTimeWorkRequest() {
        repository.scheduleOneTimeWorkRequest()
    }

    fun scheduleOneTimeWorkRequestWithData() {
        viewModelScope.launch {
            repository.scheduleOneTimeWorkRequestWithData()
        }
    }

    fun scheduleOneTimeWorkRequestWithConstraints() {
        viewModelScope.launch {
            repository.scheduleOneTimeWorkRequestWithConstraints()
        }
    }

    fun schedulePeriodicWorkRequest() {
        viewModelScope.launch {
            repository.schedulePeriodicWorkRequest()
        }
    }
}
