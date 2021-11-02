package com.sekthdroid.compose.marvel.data

import com.sekthdroid.compose.marvel.data.sources.api.NetworkDatasource
import com.sekthdroid.compose.marvel.data.sources.room.RoomDatasource
import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class DefaultCharactersRepository @Inject constructor(
    private val roomDatasource: RoomDatasource,
    private val networkDatasource: NetworkDatasource
) : CharactersRepository {

    override fun getCharacters(): Flow<List<MarvelCharacter>> {
        return flow { emit(roomDatasource.get()) }.onCompletion { emitAll(networkFlow()) }
    }

    private fun networkFlow(): Flow<List<MarvelCharacter>> =
        flow {
            runCatching { networkDatasource.getCharacters() }
                .onSuccess {
                    roomDatasource.insert(*it.toTypedArray())
                    emit(it)
                }
        }

    override suspend fun getCharacter(id: String?): MarvelCharacter? {
        if (id.isNullOrEmpty()) return null
        return roomDatasource.get(id)
    }
}
