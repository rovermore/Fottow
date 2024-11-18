package com.fottow.fottow.domain.photo.repository

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result

interface PhotoRepository {
    fun uploadPhoto(imagePath: String): Result<Boolean, Error>
}