package com.fottow.fottow.presentation.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.FileResolver
import com.fottow.fottow.domain.photo.usecase.UploadPhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val fileResolver: FileResolver,
    private val updatePhotoUseCase: UploadPhotoUseCase
): ViewModel() {


    fun selectImage(uri: Uri?) {
        viewModelScope.launch(Dispatchers.IO) {
            uri?.let {
                val imagePath = fileResolver.getRealPathFromUri(it)
                if (imagePath != null) {
                    updatePhotoUseCase.uploadPhoto(imagePath)
                }
            }
        }
    }

}