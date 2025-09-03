package com.fottow.fottow.presentation.error

sealed class ErrorUi(val message: String = "") {
    data object None : ErrorUi()
    data object CredentialsError : ErrorUi("Error de credenciales")
    data object NetworkError : ErrorUi("Error de conexión")
    data class UnknownError(val msg: String) : ErrorUi(msg)
    data class DataSentError(val msg: String) : ErrorUi(msg)
    data class ServerError(val msg: String) : ErrorUi(msg)
}