package com.fottow.fottow.data.photo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadPhotoResponse(
    val message: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("total_faces")
    val totalFaces: Int
) {
}