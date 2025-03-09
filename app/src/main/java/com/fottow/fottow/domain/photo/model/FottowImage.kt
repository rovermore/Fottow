package com.fottow.fottow.domain.photo.model

import kotlinx.serialization.Serializable

@Serializable
data class FottowImageResponse(
    val images: List<FottowImage>
)

@Serializable
data class FottowImage(
    val url: String,
    val date: String
) {
}