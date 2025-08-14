package com.hadiyarajesh.week6.day2_networking

sealed interface NetworkingScreenViewState {
    data object Initial : NetworkingScreenViewState

    data object Loading : NetworkingScreenViewState

    data class Success(val photos: List<Photo>) : NetworkingScreenViewState

    data class Error(val message: String) : NetworkingScreenViewState
}
