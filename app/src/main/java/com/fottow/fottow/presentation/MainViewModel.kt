package com.fottow.fottow.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.fottow.fottow.FileResolver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MainViewModel(
    private val fileResolver: FileResolver
): ViewModel() {


    fun selectImage(uri: Uri?) {
        uri?.let {
           val imagePath =  fileResolver.getRealPathFromUri(it)
        }
    }

}