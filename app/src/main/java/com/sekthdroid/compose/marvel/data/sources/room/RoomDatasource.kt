package com.sekthdroid.compose.marvel.data.sources.room

import com.sekthdroid.marvel.domain.models.MarvelCharacter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDatasource @Inject constructor(private val charactersDao: CharactersDao) {
    suspend fun insert(vararg character: MarvelCharacter) {
        character.forEach {
            charactersDao.insertAll(it.toEntity())
            charactersDao.insertAll(*it.toResourcesEntity().toTypedArray())
        }
    }

    suspend fun get(): List<MarvelCharacter> {
        return charactersDao.getAll()
            .map {
                it.toMarvelCharacter()
            }
    }

    suspend fun get(id: String): MarvelCharacter? {
        return charactersDao.get(id)?.toMarvelCharacter()
    }
}
