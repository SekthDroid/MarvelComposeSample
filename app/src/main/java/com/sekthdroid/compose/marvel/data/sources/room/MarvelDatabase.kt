package com.sekthdroid.compose.marvel.data.sources.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterEntity::class, ThumbnailEntity::class, ResourceEntity::class],
    version = 1
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}