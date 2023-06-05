package com.sekthdroid.marvel.data.di

import com.sekthdroid.marvel.data.BuildConfig
import com.sekthdroid.marvel.data.api.ApiEndpoints
import com.sekthdroid.marvel.data.api.plugins.AndroidLogger
import com.sekthdroid.marvel.data.api.plugins.ApiAuthenticationPlugin
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val NetworkModule = module {
    single {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
                logger = AndroidLogger()
            }

            install(ContentNegotiation) {
                json(get())
            }

            install(ApiAuthenticationPlugin) {
                privateKey = BuildConfig.PrivateKey
                publicKey = BuildConfig.PublicKey
            }
        }
    }

    single {
        ApiEndpoints(BuildConfig.BaseUrl)
    }
}