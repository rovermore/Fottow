package com.fottow.fottow.data.photo

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.photo.repository.PhotoRepository

class PhotoRepositoryImpl(): PhotoRepository {
    override fun uploadPhoto(imagePath: String): Result<Boolean, Error> {
        TODO("Not yet implemented")
    }
}