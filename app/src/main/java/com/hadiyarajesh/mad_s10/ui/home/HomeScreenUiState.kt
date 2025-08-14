package com.hadiyarajesh.mad_s10.ui.home

import com.hadiyarajesh.mad_s10.data.entity.Image

/**
 * Sealed class to represent UI states in [HomeScreen]
 */
internal sealed interface HomeScreenUiState {
    data object Initial : HomeScreenUiState
    data object Loading : HomeScreenUiState
    data class Success(val data: Image) : HomeScreenUiState
    data class Error(val msg: String) : HomeScreenUiState
}
