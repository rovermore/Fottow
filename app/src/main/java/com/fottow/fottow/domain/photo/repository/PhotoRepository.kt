package com.fottow.fottow.domain.photo.repository

import com.fottow.fottow.data.photo.UploadPhotoResponse
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result

interface PhotoRepository {
    suspend fun uploadPhoto(imagePath: String): Result<UploadPhotoResponse, Error>
}