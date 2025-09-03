package com.fottow.fottow.data.base

import com.fottow.fottow.domain.base.Error

class APIErrorMapper() {

    fun map(apiError: APIError): Error = when (apiError) {
        is APIError.InternalServerError -> Error.InternalServerError(apiError.message)
        is APIError.ServiceUnavailable -> Error.UncompletedOperation(apiError.message)
        is APIError.NotFound -> Error.NotFound(apiError.message)
        is APIError.TimeOut -> Error.ConnectionError(apiError.message)
        is APIError.NotProcessableEntity -> Error.BadRequest(apiError.message)
        is APIError.Unauthorized -> Error.Unauthorized(apiError.message)
        is APIError.Forbidden -> Error.Unauthorized(apiError.message)
        is APIError.BadRequest -> Error.BadRequest(apiError.message)
        is APIError.UnmappedError -> Error.Unmapped(apiError.message)
    }
}