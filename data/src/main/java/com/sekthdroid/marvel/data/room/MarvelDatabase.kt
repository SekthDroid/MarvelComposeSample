package com.sekthdroid.marvel.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterEntity::class, ResourceEntity::class],
    version = 1
)
internal abstract class MarvelDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}