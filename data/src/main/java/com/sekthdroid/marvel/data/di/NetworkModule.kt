package com.sekthdroid.marvel.data.di

import com.sekthdroid.marvel.data.BuildConfig
import com.sekthdroid.marvel.data.api.ApiEndpoints
import com.sekthdroid.marvel.data.api.features.AndroidLogger
import com.sekthdroid.marvel.data.api.features.ApiAuthenticationFeature
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Singleton
    @Provides
    fun jsonConfig(): Json {
        return Json(KotlinxSerializer.DefaultJson) {
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

            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }

            install(ApiAuthenticationFeature)
        }
    }

    @Provides
    fun apiEndpoints(): ApiEndpoints {
        return ApiEndpoints(BuildConfig.BaseUrl)
    }
}