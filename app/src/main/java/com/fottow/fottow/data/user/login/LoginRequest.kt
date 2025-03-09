package com.fottow.fottow.data.user.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LoginRequest(
    @SerialName("username")
    val userName: String,
    val password: String
) {
}