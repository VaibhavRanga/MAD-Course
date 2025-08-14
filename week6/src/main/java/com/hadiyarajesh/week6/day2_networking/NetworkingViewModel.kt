package com.hadiyarajesh.week6.day2_networking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.week6.day2_networking.NetworkingScreenViewState.Error
import com.hadiyarajesh.week6.day2_networking.NetworkingScreenViewState.Initial
import com.hadiyarajesh.week6.day2_networking.NetworkingScreenViewState.Loading
import com.hadiyarajesh.week6.day2_networking.NetworkingScreenViewState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class NetworkingViewModel @Inject constructor(
    private val networkingRepository: NetworkingRepository
) : ViewModel() {
    private val _viewState =
        MutableStateFlow<NetworkingScreenViewState>(Initial)
    val viewState: StateFlow<NetworkingScreenViewState> get() = _viewState

    init {
        getAllPhotos()
//        getAllPhotosCall()
//        getAllPhotosResponse()
    }

    fun getAllPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.value = Loading

            try {
                val retrievedPhotos = networkingRepository.getAllPhotos()
                _viewState.value = Success(retrievedPhotos)
            } catch (e: IOException) {
                _viewState.value = Error(e.message ?: "Unknown error")
            }
        }
    }

    fun getAllPhotosCall() {
        _viewState.value = Loading

        networkingRepository.getAllPhotosCall(
            onSuccess = { photos ->
                _viewState.value = Success(photos)
            }, onFailure = { t: Throwable ->
                _viewState.value = Error(t.message ?: "Unknown error")
            }
        )
    }

    fun getAllPhotosResponse() {
        viewModelScope.launch {
            networkingRepository.getAllPhotosResponse(
                onSuccess = { photos ->
                    _viewState.value = Success(photos)
                }, onFailure = { t: Throwable ->
                    _viewState.value = Error(t.message ?: "Unknown error")
                }
            )
        }
    }

    fun createPhoto(photo: Photo) {
        viewModelScope.launch {
            val photo = networkingRepository.createPhoto(photo)
        }
    }
}
