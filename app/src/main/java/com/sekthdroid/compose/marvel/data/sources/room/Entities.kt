package com.sekthdroid.compose.marvel.data.sources.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

data class CompleteCharacter(
    @Embedded
    val character: CharacterEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val thumbnail: ThumbnailEntity? = null,

    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val resources: List<ResourceEntity> = emptyList()
)

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "description")
    val description: String?
)

@Entity(tableName = "thumbnails")
class ThumbnailEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val url: String?,
    val extension: String?,
    val characterId: Long
)

enum class ResourceType {
    Comic, Story, Serie
}

@Entity(tableName = "resources", primaryKeys = ["characterId", "resourceUri"])
class ResourceEntity(
    val characterId: Long,
    val resourceUri: String,
    val name: String?,
    val type: ResourceType?
)
