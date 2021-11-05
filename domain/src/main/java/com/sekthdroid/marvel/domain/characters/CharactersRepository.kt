package com.sekthdroid.marvel.domain.characters

import com.sekthdroid.marvel.domain.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface CharactersRepository {
    fun getCharacters(): Flow<List<MarvelCharacter>> = emptyFlow()
    suspend fun getCharacter(id: String?): MarvelCharacter? = null
}