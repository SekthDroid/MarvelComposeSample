package com.sekthdroid.compose.marvel.data.di

import com.sekthdroid.compose.marvel.BuildConfig
import com.sekthdroid.compose.marvel.data.sources.api.ApiEndpoints
import com.sekthdroid.compose.marvel.data.sources.api.NetworkDatasource
import com.sekthdroid.compose.marvel.data.sources.api.features.AndroidLogger
import com.sekthdroid.compose.marvel.data.sources.api.features.ApiAuthenticationFeature
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
class NetworkModule {
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

    @Singleton
    @Provides
    fun networkDatasource(httpClient: HttpClient, apiEndpoints: ApiEndpoints): NetworkDatasource {
        return NetworkDatasource(httpClient, apiEndpoints)
    }
}