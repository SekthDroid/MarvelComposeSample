package com.sekthdroid.marvel.data.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

internal data class CompleteCharacter(
    @Embedded
    val character: CharacterEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val resources: List<ResourceEntity> = emptyList()
)

@Entity(tableName = "characters")
internal data class CharacterEntity(
    @PrimaryKey
    val id: Long,
    val name: String?,
    val thumbnail: String?,
    val description: String?
)

internal enum class ResourceType {
    Comic, Story, Serie
}

@Entity(tableName = "resources", primaryKeys = ["characterId", "resourceUri"])
internal class ResourceEntity(
    val characterId: Long,
    val resourceUri: String,
    val name: String?,
    val type: ResourceType?
)
