package com.fottow.fottow.data.user.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)