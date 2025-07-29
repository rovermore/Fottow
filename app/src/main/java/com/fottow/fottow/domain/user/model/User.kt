package com.fottow.fottow.domain.user.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String = "",
    val name: String = "",
    val profileImage: String = ""
) {
}