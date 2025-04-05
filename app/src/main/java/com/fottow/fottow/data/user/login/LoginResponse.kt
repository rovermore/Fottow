package com.fottow.fottow.data.user.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
    @SerialName("user")
    val userName: UserName
)

@Serializable
data class UserName(
    @SerialName("username")
    val userName: String,
    val nick: String?,
    @SerialName("profile_image")
    val profileImage: String?
)