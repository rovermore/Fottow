package com.fottow.fottow.domain.photo.usecase

import com.fottow.fottow.data.photo.UploadPhotoResponse
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.photo.model.FottowImage
import com.fottow.fottow.domain.photo.repository.PhotoRepository

class PhotoUseCase(
    private val photoRepository: PhotoRepository
) {

    suspend fun uploadPhoto(imagePath: String): Result<UploadPhotoResponse, Error> {
        return photoRepository.uploadPhoto(imagePath)
    }

    suspend fun getImages(): Result<List<FottowImage>, Error> =
        photoRepository.getImages()

    suspend fun uploadIdentificationSelfie(imagePath: String): Result<UploadPhotoResponse, Error> {
        return photoRepository.uploadPhoto(imagePath)
    }
}