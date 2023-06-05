package com.sekthdroid.domain.shared.characters

import com.sekthdroid.domain.shared.models.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface CharactersRepository {
    fun getCharacters(): Flow<List<MarvelCharacter>> = emptyFlow()
    suspend fun getCharacter(id: String?): MarvelCharacter? = null
}