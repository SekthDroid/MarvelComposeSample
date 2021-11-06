package com.sekthdroid.marvel.data.api

import com.sekthdroid.marvel.domain.models.MarvelCharacter
import com.sekthdroid.marvel.domain.models.Page
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class ApiEndpoints(private val baseUrl: String) {
    fun characters() = "$baseUrl/characters"
}

internal class NetworkDatasource @Inject constructor(
    private val httpClient: HttpClient,
    private val endpoints: ApiEndpoints
) {
    suspend fun getCharacters(): Page<MarvelCharacter> {
        val response = httpClient.get<MarvelResponse<CharacterApiModel>>(endpoints.characters())
        return response.data.toPage { it.toCharacter() }
    }
}