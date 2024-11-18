package com.fottow.fottow.data.base

import com.fottow.fottow.domain.base.Failure
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.Success
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode


class CallExecutor(
    private val networkExceptionsMapper: NetworkExceptionsMapper
) {

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    suspend inline fun <reified T> executeKtorCall(call: suspend () -> HttpResponse): Result<T, APIError> {
        return try {
            val response = call()
            if (response.status == HttpStatusCode.OK && response.body<T>() != null) {
                Success(response.body<T>())
            } else {
                val errorBody = response.body() ?: ""
                Failure(networkExceptionsMapper.mapNetworkException(
                    response.status.value,
                    errorBody))
            }
        } catch (e: Exception) {
            Failure(networkExceptionsMapper.mapException(e))
        }
    }
}