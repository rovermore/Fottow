package com.fottow.fottow.data.base

import android.util.Log
import com.fottow.fottow.data.user.local.UserLocalDatasource
import com.fottow.fottow.domain.base.get
import com.gyanoba.inspektor.Inspektor
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

class FottowKtorClient(
    private val userLocalDatasource: UserLocalDatasource
) {
    fun getClient() =
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("KtorClient", message)
                    }
                }
                level = LogLevel.ALL
            }
             install(Inspektor) {
                 level = com.gyanoba.inspektor.LogLevel.BODY
             }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken = userLocalDatasource.getToken().get().toString(),
                            refreshToken = null
                        )
                    }
                }
            }
        }
}
