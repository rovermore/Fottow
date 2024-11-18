package com.fottow.fottow.data.base

import java.lang.Exception

class NetworkExceptionsMapper() {
    fun mapNetworkException(code: Int, message: String?) : APIError =
        when (code) {
            500 -> APIError.InternalServerError(message ?: "")
            503 -> APIError.ServiceUnavailable(message ?: "")
            422 -> APIError.NotProcessableEntity(message ?: "")
            404 -> APIError.NotFound(message ?: "")
            408 -> APIError.TimeOut(message ?: "")

            else -> APIError.UnmappedError(code, message ?: "")
        }

    fun mapException(e: Exception): APIError = APIError.UnmappedError(-1, e.message ?: "")
}