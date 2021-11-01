package com.sekthdroid.compose.marvel.data.sources.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CharactersDao {
    @Transaction
    @Query("SELECT * FROM characters ORDER BY name")
    suspend fun getAll(): List<CompleteCharacter>

    @Transaction
    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun get(id: String): CompleteCharacter?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg thumbnail: ThumbnailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg resources: ResourceEntity)
}