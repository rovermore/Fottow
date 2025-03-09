package com.fottow.fottow.domain.photo.repository

import com.fottow.fottow.data.photo.UploadPhotoResponse
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.photo.model.FottowImage

interface PhotoRepository {
    suspend fun uploadPhoto(imagePath: String): Result<UploadPhotoResponse, Error>
    suspend fun getImages(): Result<List<FottowImage>, Error>
}