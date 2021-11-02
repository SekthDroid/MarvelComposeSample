package com.sekthdroid.compose.marvel.data.sources.api.features

import com.sekthdroid.compose.marvel.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpClientFeature
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.parameter
import io.ktor.util.AttributeKey
import java.math.BigInteger
import java.security.MessageDigest

class ApiAuthenticationFeature {
    class Config

    companion object : HttpClientFeature<Config, ApiAuthenticationFeature> {
        override val key: AttributeKey<ApiAuthenticationFeature> =
            AttributeKey("AuthenticationMiddleware")

        override fun install(feature: ApiAuthenticationFeature, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                with(context) {
                    val ts = System.currentTimeMillis()
                    parameter("apikey", BuildConfig.PublicKey)
                    parameter("ts", ts)
                    parameter(
                        "hash",
                        "${ts}${BuildConfig.PrivateKey}${BuildConfig.PublicKey}".md5()
                    )
                }

                proceedWith(subject)
            }
        }

        override fun prepare(block: Config.() -> Unit): ApiAuthenticationFeature {
            return ApiAuthenticationFeature()
        }

        private fun String.md5(): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(toByteArray()))
                .toString(16)
                .padStart(32, '0')
        }
    }
}