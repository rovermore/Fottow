package com.fottow.fottow.data.user.network

import com.fottow.fottow.data.base.APIError
import com.fottow.fottow.data.base.CallExecutor
import com.fottow.fottow.data.base.FottowKtorClient
import com.fottow.fottow.data.base.FottowURL
import com.fottow.fottow.data.user.login.LoginRequest
import com.fottow.fottow.data.user.login.LoginResponse
import com.fottow.fottow.data.user.register.RegisterRequest
import com.fottow.fottow.data.user.register.RegisterResponse
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.Success
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.headers

class UserNetworkDatasource(
    private val client: FottowKtorClient,
    private val callExecutor: CallExecutor
) {
    suspend fun logUser(userName: String, password: String, token: String): Result<LoginResponse, APIError> {
        val call : suspend () -> HttpResponse = {
            client.getClient().request(FottowURL.LOGIN) {
                method = HttpMethod.Companion.Post
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.ContentType, "application/json")
                }
                setBody(LoginRequest(userName, password, token))
            }
        }
        return callExecutor.executeKtorCall<LoginResponse>(call)
    }

    suspend fun userRegister(userName: String, password: String, nickname: String, token: String): Result<RegisterResponse, APIError> {
        val call : suspend () -> HttpResponse = {
            client.getClient().request(FottowURL.REGISTER) {
                method = HttpMethod.Companion.Post
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.ContentType, "application/json")
                }
                setBody(RegisterRequest(userName, password, nickname, token))
            }
        }
        return callExecutor.executeKtorCall<RegisterResponse>(call)
    }

    suspend fun logout(): Result<Boolean, APIError> {
        val call : suspend () -> HttpResponse = {
            client.getClient().request(FottowURL.LOGOUT) {
                method = HttpMethod.Companion.Post
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.ContentType, "application/json")
                }
            }
        }
        call()
        return Success(true)
    }
}