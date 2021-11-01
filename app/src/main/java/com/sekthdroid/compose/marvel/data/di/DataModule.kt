package com.sekthdroid.compose.marvel.data.di

import com.sekthdroid.compose.marvel.data.CharactersRepository
import com.sekthdroid.compose.marvel.data.DefaultCharactersRepository
import com.sekthdroid.compose.marvel.data.sources.api.NetworkDatasource
import com.sekthdroid.compose.marvel.data.sources.room.RoomDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun charactersRepository(
        roomDatasource: RoomDatasource,
        networkDatasource: NetworkDatasource
    ): CharactersRepository {
        return DefaultCharactersRepository(
            roomDatasource = roomDatasource,
            networkDatasource = networkDatasource
        )
    }
}