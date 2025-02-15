package com.fottow.fottow.data.login

import com.fottow.fottow.data.base.APIError
import com.fottow.fottow.data.base.CallExecutor
import com.fottow.fottow.data.base.FottowKtorClient
import com.fottow.fottow.domain.base.Result
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod

class LoginNetworkDatasource(
    private val client: FottowKtorClient,
    private val callExecutor: CallExecutor
) {
    suspend fun logUser(userName: String, password: String): Result<LoginResponse, APIError> {
        val call : suspend () -> HttpResponse = {
            client.getClient().request("www.test.url.com") {
                method = HttpMethod.Get
                setBody(LoginRequest(userName, password))
            }
        }
        return callExecutor.executeKtorCall<LoginResponse>(call)
    }
}