package com.sekthdroid.compose.marvel.data.di

import com.sekthdroid.compose.marvel.data.CharactersRepository
import com.sekthdroid.compose.marvel.data.DefaultCharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindCharactersRepository(characterRepository: DefaultCharactersRepository): CharactersRepository
}