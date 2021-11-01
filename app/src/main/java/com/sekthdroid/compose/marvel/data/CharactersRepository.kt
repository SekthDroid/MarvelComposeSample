package com.sekthdroid.compose.marvel.data

import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface CharactersRepository {
    fun getCharacters(): Flow<List<MarvelCharacter>> = emptyFlow()
    suspend fun getCharacter(id: String?): MarvelCharacter? = null
}