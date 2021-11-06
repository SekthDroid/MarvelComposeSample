package com.sekthdroid.marvel.data.di

import android.content.Context
import androidx.room.Room
import com.sekthdroid.marvel.data.room.CharactersDao
import com.sekthdroid.marvel.data.room.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

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