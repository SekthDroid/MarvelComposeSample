package com.sekthdroid.compose.marvel.data.sources.api.features

import com.sekthdroid.compose.marvel.BuildConfig
import com.sekthdroid.compose.marvel.md5
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpClientFeature
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.parameter
import io.ktor.util.AttributeKey

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
    }
}