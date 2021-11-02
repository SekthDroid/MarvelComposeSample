package com.sekthdroid.compose.marvel.data.sources.api

import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import com.sekthdroid.compose.marvel.domain.MarvelResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class ApiEndpoints(private val baseUrl: String) {
    fun characters() = "$baseUrl/characters"
}

class NetworkDatasource @Inject constructor(
    private val httpClient: HttpClient,
    private val endpoints: ApiEndpoints
) {
    suspend fun getCharacters(): List<MarvelCharacter> {
        val response = httpClient.get<MarvelResponse>(endpoints.characters())
        return response.data.results
    }
}