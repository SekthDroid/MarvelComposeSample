package com.sekthdroid.marvel.data.di

import com.sekthdroid.marvel.data.BuildConfig
import com.sekthdroid.marvel.data.api.ApiEndpoints
import com.sekthdroid.marvel.data.api.plugins.AndroidLogger
import com.sekthdroid.marvel.data.api.plugins.ApiAuthenticationPlugin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Singleton
    @Provides
    fun jsonConfig(): Json {
        return Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    @Singleton
    @Provides
    fun client(json: Json): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
                logger = AndroidLogger()
            }

            install(ContentNegotiation) {
                json(json)
            }

            install(ApiAuthenticationPlugin) {
                privateKey = BuildConfig.PrivateKey
                publicKey = BuildConfig.PublicKey
            }
        }
    }

    @Provides
    fun apiEndpoints(): ApiEndpoints {
        return ApiEndpoints(BuildConfig.BaseUrl)
    }
}