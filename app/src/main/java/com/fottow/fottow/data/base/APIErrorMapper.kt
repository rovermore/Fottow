package com.fottow.fottow.data.base

import com.fottow.fottow.domain.base.Error

class APIErrorMapper() {

    fun map(apiError: APIError): Error = when (apiError) {
        is APIError.InternalServerError -> Error.UncompletedOperation(apiError.message)
        is APIError.ServiceUnavailable -> Error.UncompletedOperation(apiError.message)
        is APIError.UnauthorizedError -> Error.Unauthorized(apiError.message)
        is APIError.NotFound -> Error.OperationCompletedWithError(apiError.message)
        is APIError.TimeOut -> Error.ConnectionError(apiError.message)
        is APIError.NotProcessableEntity -> Error.Unauthorized(apiError.message)
        else -> Error.Unmapped(apiError.message)
    }
}