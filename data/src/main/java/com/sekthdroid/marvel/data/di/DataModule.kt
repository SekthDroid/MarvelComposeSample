package com.sekthdroid.marvel.data.di

import com.sekthdroid.marvel.data.DefaultCharactersRepository
import com.sekthdroid.marvel.domain.characters.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindCharactersRepository(characterRepository: DefaultCharactersRepository): CharactersRepository
}