package com.fottow.fottow.data.photo

import kotlinx.serialization.Serializable

@Serializable
data class UploadPhotoResponse(
    val result: Boolean
) {
}