package com.sekthdroid.compose.marvel.data.di

import android.content.Context
import androidx.room.Room
import com.sekthdroid.compose.marvel.data.sources.room.CharactersDao
import com.sekthdroid.compose.marvel.data.sources.room.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun charactersDao(marvelDatabase: MarvelDatabase): CharactersDao {
        return marvelDatabase.charactersDao()
    }

    @Singleton
    @Provides
    fun room(@ApplicationContext appContext: Context): MarvelDatabase {
        return Room.databaseBuilder(appContext, MarvelDatabase::class.java, "marvel-characters")
            .build()
    }
}