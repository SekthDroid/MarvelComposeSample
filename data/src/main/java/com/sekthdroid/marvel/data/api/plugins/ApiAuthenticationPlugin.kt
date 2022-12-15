package com.sekthdroid.marvel.data.api.plugins

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.parameter
import io.ktor.util.AttributeKey
import java.math.BigInteger
import java.security.MessageDigest

internal class ApiAuthenticationPlugin(val config: Config) {

    class Config {
        var privateKey: String = ""
        var publicKey: String = ""
    }

    companion object : HttpClientPlugin<Config, ApiAuthenticationPlugin> {
        private const val ApiKey = "apikey"
        private const val TimeStamp = "ts"
        private const val Hash = "hash"

        override val key: AttributeKey<ApiAuthenticationPlugin> =
            AttributeKey("AuthenticationMiddleware")

        override fun install(plugin: ApiAuthenticationPlugin, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                with(context) {
                    val ts = System.currentTimeMillis()
                    println("making request with $ts")
                    parameter(ApiKey, plugin.config.publicKey)
                    parameter(TimeStamp, ts)
                    parameter(
                        Hash,
                        "${ts}${plugin.config.privateKey}${plugin.config.publicKey}".md5()
                    )
                }

                proceedWith(subject)
            }
        }

        override fun prepare(block: Config.() -> Unit): ApiAuthenticationPlugin {
            return ApiAuthenticationPlugin(Config().apply(block))
        }

        private fun String.md5(): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(toByteArray()))
                .toString(16)
                .padStart(32, '0')
        }
    }
}