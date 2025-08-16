package com.fottow.fottow.presentation.upload

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.FileResolver
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.base.then
import com.fottow.fottow.domain.photo.usecase.PhotoUseCase
import com.fottow.fottow.domain.user.usecase.UserUseCase
import com.fottow.fottow.presentation.UploadResultManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UploadViewModel(
    private val fileResolver: FileResolver,
    private val updatePhotoUseCase: PhotoUseCase,
    private val userUseCase: UserUseCase
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _uploadSuccessful = MutableStateFlow(false)
    val uploadSuccessful: StateFlow<Boolean> get() = _uploadSuccessful

    private val _onError = MutableStateFlow(false)
    val onError: StateFlow<Boolean> get() = _onError

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        // Escuchar resultados del servicio
        viewModelScope.launch {
            UploadResultManager.uploadResult.collect { result ->
                if (result.success) {
                    _uploadSuccessful.value = true
                } else {
                    _onError.value = true
                    _errorMessage.value = result.errorMessage
                }
            }
        }
    }

    val goToIdentificationScreen: StateFlow<Boolean> = flow {
        userUseCase.getUser().map {
            emit(it.profileImage.isEmpty())
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun selectImage(uri: Uri?) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            uri?.let {
                val imagePath = fileResolver.getRealPathFromUri(it)
                if (imagePath != null) {
                    updatePhotoUseCase.uploadPhoto(imagePath)
                        .map {
                            _uploadSuccessful.value = true
                        }.mapFailure {
                            _onError.value = true
                            _errorMessage.value = "Error al subir la foto"
                        }.then {
                            _isLoading.value = false
                        }
                }
            }
        }
    }

    fun dialogDismissed() {
        _uploadSuccessful.value = false
        _onError.value = false
        _errorMessage.value = null
    }

}