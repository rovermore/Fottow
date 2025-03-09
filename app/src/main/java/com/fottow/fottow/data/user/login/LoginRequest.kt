package com.fottow.fottow.data.user.login

import kotlinx.serialization.Serializable

@Serializable
class LoginRequest(
    val userName: String,
    val password: String
) {
}