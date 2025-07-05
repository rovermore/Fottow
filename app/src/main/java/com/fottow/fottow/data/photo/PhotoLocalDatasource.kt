package com.fottow.fottow.data.photo

import com.fottow.fottow.data.user.local.FottowDataStore
import com.fottow.fottow.domain.photo.model.FottowImage
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Success
import com.fottow.fottow.domain.base.Failure
import kotlinx.serialization.json.Json

class PhotoLocalDatasource(
    private val dataStore: FottowDataStore
) {
    
    companion object {
        const val PHOTOS_LIST_KEY = "PHOTOS_K_LIST_KEY"
    }

    fun savePhotos(photos: List<FottowImage>): Result<Boolean, Error> {
        val json = Json.encodeToString(photos)
        return dataStore.save(PHOTOS_LIST_KEY, json)
    }

    fun getPhotos(): Result<List<FottowImage>, Error> {
        return when (val result = dataStore.read<String>(PHOTOS_LIST_KEY)) {
            is Success -> {
                try {
                    val photos = Json.decodeFromString<List<FottowImage>>(result.value)
                    Success(photos)
                } catch (e: Exception) {
                    Failure(Error.UncompletedOperation("Error deserializing photos: ${e.message}"))
                }
            }

            is Failure -> Failure(result.reason)
        }
    }
}