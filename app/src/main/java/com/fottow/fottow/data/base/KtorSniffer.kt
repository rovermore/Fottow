package com.fottow.fottow.data.base

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.content.OutgoingContent
import io.ktor.util.AttributeKey

class KtorSniffer private constructor(
    private val logLevel: SnifferLogLevel = SnifferLogLevel.ALL,
    private val logger: SnifferLogger = DefaultSnifferLogger()
) {

    enum class SnifferLogLevel {
        NONE, BASIC, HEADERS, BODY, ALL
    }

    interface SnifferLogger {
        fun log(message: String)
    }

    class DefaultSnifferLogger : SnifferLogger {
        override fun log(message: String) {
            Log.d("KtorSniffer", message)
        }
    }

    class Config {
        var logLevel: SnifferLogLevel = SnifferLogLevel.ALL
        var logger: SnifferLogger = DefaultSnifferLogger()
    }

    companion object Plugin : HttpClientPlugin<Config, KtorSniffer> {
        override val key: AttributeKey<KtorSniffer> = AttributeKey("KtorSniffer")

        override fun prepare(block: Config.() -> Unit): KtorSniffer {
            val config = Config().apply(block)
            return KtorSniffer(config.logLevel, config.logger)
        }

        override fun install(plugin: KtorSniffer, scope: HttpClient) {
            if (plugin.logLevel == SnifferLogLevel.NONE) return

            // Intercept requests only
            scope.requestPipeline.intercept(io.ktor.client.request.HttpRequestPipeline.Before) {
                plugin.logRequest(context)
            }
        }
    }

    private fun logRequest(request: HttpRequestBuilder) {
        try {
            logger.log("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════")
            logger.log("║ REQUEST")
            logger.log("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════")

            if (logLevel == SnifferLogLevel.BASIC || logLevel == SnifferLogLevel.HEADERS || logLevel == SnifferLogLevel.ALL) {
                logger.log("║ Method: ${request.method}")
                logger.log("║ URL: ${request.url}")
            }

            if (logLevel == SnifferLogLevel.HEADERS || logLevel == SnifferLogLevel.ALL) {
                logger.log("║ Headers:")
                request.headers.entries().forEach { (key, values) ->
                    values.forEach { value ->
                        logger.log("║   $key: $value")
                    }
                }
            }

            if (logLevel == SnifferLogLevel.BODY || logLevel == SnifferLogLevel.ALL) {
                request.body?.let { body ->
                    when (body) {
                        is OutgoingContent.ByteArrayContent -> {
                            logger.log("║ Body:")
                            logger.log("║   ${String(body.bytes())}")
                        }
                        else -> {
                            logger.log("║ Body: ${body::class.simpleName}")
                        }
                    }
                }
            }

            logger.log("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════")
        } catch (e: Exception) {
            logger.log("║ Error logging request: ${e.message}")
        }
    }
}
