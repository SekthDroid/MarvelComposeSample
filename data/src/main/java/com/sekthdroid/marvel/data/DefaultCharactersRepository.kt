package com.sekthdroid.marvel.data

import com.sekthdroid.domain.shared.characters.CharactersRepository
import com.sekthdroid.domain.shared.models.MarvelCharacter
import com.sekthdroid.marvel.data.api.NetworkDatasource
import com.sekthdroid.marvel.data.sql.LocalDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion

internal class DefaultCharactersRepository (
    private val roomDatasource: LocalDatasource,
    private val networkDatasource: NetworkDatasource
) : CharactersRepository {

    override fun getCharacters(): Flow<List<MarvelCharacter>> {
        return flow { emit(roomDatasource.get()) }.onCompletion { emitAll(networkFlow()) }
    }

    private fun networkFlow(): Flow<List<MarvelCharacter>> =
        flow {
            runCatching { networkDatasource.getCharacters() }
                .onSuccess {
                    roomDatasource.insert(*it.results.toTypedArray())

                    // TODO: 2/11/21 We will handle the pagination in the future
                    emit(it.results)
                }
        }

    override suspend fun getCharacter(id: String?): MarvelCharacter? {
        if (id.isNullOrEmpty()) return null
        return roomDatasource.get(id.toLong())
    }
}
