package com.fottow.fottow.presentation.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.fottow.fottow.FileResolver
import com.fottow.fottow.domain.photo.usecase.UploadPhotoUseCase

class MainViewModel(
    private val fileResolver: FileResolver,
    private val updatePhotoUseCase: UploadPhotoUseCase
): ViewModel() {


    fun selectImage(uri: Uri?) {
        uri?.let {
           val imagePath =  fileResolver.getRealPathFromUri(it)
            updatePhotoUseCase
        }
    }

}