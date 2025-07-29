package com.fottow.fottow.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.base.then
import com.fottow.fottow.domain.photo.model.FottowImage
import com.fottow.fottow.domain.photo.usecase.PhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val photoUseCase: PhotoUseCase
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    private val _success = MutableStateFlow<List<FottowImage>>(emptyList())
    val success: StateFlow<List<FottowImage>> get() = _success.asStateFlow()

    private val _onError = MutableStateFlow(false)
    val onError: StateFlow<Boolean> get() = _onError.asStateFlow()

    init {
        getImages()
    }

    fun getImages() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.emit( true)
            photoUseCase.getImages()
                .map { result ->
                    _success.emit(result.sortedByDescending { it.date })
                }.mapFailure {
                    _onError.emit(true )
                }.then {
                    _isLoading.emit( false )
                }
        }
    }

}