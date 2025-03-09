package com.fottow.fottow.data.user

import com.fottow.fottow.data.base.APIError
import com.fottow.fottow.data.base.CallExecutor
import com.fottow.fottow.data.base.FottowKtorClient
import com.fottow.fottow.data.user.login.LoginRequest
import com.fottow.fottow.data.user.login.LoginResponse
import com.fottow.fottow.data.user.register.RegisterRequest
import com.fottow.fottow.domain.base.Result
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.headers
import kotlinx.serialization.json.Json

class UserNetworkDatasource(
    private val client: FottowKtorClient,
    private val callExecutor: CallExecutor
) {
    suspend fun logUser(userName: String, password: String): Result<LoginResponse, APIError> {
        val call : suspend () -> HttpResponse = {
            client.getClient().request("https://778meigv57.execute-api.us-east-1.amazonaws.com/dev/auth/login") {
                method = HttpMethod.Post
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.ContentType, "application/json")
                }
                setBody(LoginRequest(userName, password))
            }
        }
        return callExecutor.executeKtorCall<LoginResponse>(call)
    }

    suspend fun userRegister(userName: String, password: String, nickname: String): Result<String, APIError> {
        val call : suspend () -> HttpResponse = {
            client.getClient().request("https://778meigv57.execute-api.us-east-1.amazonaws.com/dev/auth/signup") {
                method = HttpMethod.Post
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.ContentType, "application/json")
                }
                setBody(RegisterRequest(userName, password, nickname))
            }
        }
        return callExecutor.executeKtorCall<String>(call)
    }
}