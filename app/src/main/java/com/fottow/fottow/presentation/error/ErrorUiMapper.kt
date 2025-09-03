package com.fottow.fottow.presentation.error

import com.fottow.fottow.domain.base.Error

class ErrorUiMapper() {
    fun map(domainError: Error): ErrorUi = when (domainError) {
        is Error.Unauthorized -> ErrorUi.CredentialsError
        is Error.ConnectionError -> ErrorUi.NetworkError
        is Error.BadRequest -> ErrorUi.DataSentError(domainError.message)
        is Error.InternalServerError -> ErrorUi.ServerError(domainError.message)


        else  -> ErrorUi.UnknownError(domainError.message)
    }
}