package com.fottow.fottow.data.user.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RegisterRequest(
    @SerialName("username")
    val userName: String,
    val password: String,
    @SerialName("nick")
    val nickName: String,
    val fcm: String
) {
}