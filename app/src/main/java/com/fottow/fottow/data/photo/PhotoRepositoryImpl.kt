package com.fottow.fottow.data.photo

import com.fottow.fottow.data.base.APIErrorMapper
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.photo.model.FottowImage
import com.fottow.fottow.domain.photo.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val photoNetworkDatasource: PhotoNetworkDatasource,
    private val apiErrorMapper: APIErrorMapper
): PhotoRepository {
    override suspend fun uploadPhoto(imagePath: String): Result<UploadPhotoResponse, Error> {
        return photoNetworkDatasource.uploadPhoto(imagePath).mapFailure {
                apiErrorMapper.map(it)
        }
    }

    override suspend fun getImages(): Result<List<FottowImage>, Error> {
        return photoNetworkDatasource.getImages()
            .map {
                it.images
            }.mapFailure {
                apiErrorMapper.map(it)
            }
    }
}