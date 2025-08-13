package com.fottow.fottow.data.photo

import com.fottow.fottow.data.base.APIErrorMapper
import com.fottow.fottow.data.user.local.UserLocalDatasource
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Failure
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.Success
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.photo.model.FottowImage
import com.fottow.fottow.domain.photo.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val photoNetworkDatasource: PhotoNetworkDatasource,
    private val photoLocalDatasource: PhotoLocalDatasource,
    private val userLocalDatasource: UserLocalDatasource,
    private val apiErrorMapper: APIErrorMapper
): PhotoRepository {
    override suspend fun uploadPhoto(imagePath: String): Result<UploadPhotoResponse, Error> {
        return photoNetworkDatasource.uploadPhoto(imagePath).mapFailure {
                apiErrorMapper.map(it)
        }
    }

    override suspend fun uploadIdentificationSelfie(imagePath: String): Result<UploadPhotoResponse, Error> {
        return photoNetworkDatasource.uploadIdentificationSelfie(imagePath)
            .map { response ->
                userLocalDatasource.getUser()
                    .map {
                        userLocalDatasource.setUser(it.copy(profileImage = response.imageUrl))
                    }
                response
            }
            .mapFailure {
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