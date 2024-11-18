package com.fottow.fottow.presentation.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.FileResolver
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.then
import com.fottow.fottow.domain.photo.usecase.UploadPhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val fileResolver: FileResolver,
    private val updatePhotoUseCase: UploadPhotoUseCase
): ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _uploadSuccessful = MutableStateFlow(true)
    val uploadSuccessful: StateFlow<Boolean> get() = _uploadSuccessful

    fun selectImage(uri: Uri?) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            uri?.let {
                val imagePath = fileResolver.getRealPathFromUri(it)
                if (imagePath != null) {
                    updatePhotoUseCase.uploadPhoto(imagePath)
                        .map {
                            _uploadSuccessful.value = true
                        }.then {
                            _isLoading.value = false
                        }
                }
            }
        }
    }

    fun dialogDismissed() {
        _uploadSuccessful.value = false
    }

}