package com.fottow.fottow.domain.photo.usecase

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.photo.repository.PhotoRepository

class UploadPhotoUseCase(
    private val photoRepository: PhotoRepository
) {

    fun uploadPhoto(imagePath: String): Result<Boolean, Error> {
        return photoRepository.uploadPhoto(imagePath)
    }
}