package com.sekthdroid.marvel.data.api

import com.sekthdroid.domain.shared.models.MarvelCharacter
import com.sekthdroid.domain.shared.models.Page
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class ApiEndpoints(private val baseUrl: String) {
    fun characters() = "$baseUrl/characters"
}

internal class NetworkDatasource(
    private val httpClient: HttpClient,
    private val endpoints: ApiEndpoints
) {
    suspend fun getCharacters(): Page<MarvelCharacter> {
        val response = httpClient.get(endpoints.characters())
            .body<MarvelResponse<CharacterApiModel>>()

        return response.data.toPage { it.toCharacter() }
    }
}