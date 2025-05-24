package com.fottow.fottow.data.user.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val token: String,
    val message: String
)