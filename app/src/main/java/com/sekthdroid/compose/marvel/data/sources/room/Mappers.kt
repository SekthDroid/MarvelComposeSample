package com.sekthdroid.compose.marvel.data.sources.room

import com.sekthdroid.compose.marvel.domain.ComicList
import com.sekthdroid.compose.marvel.domain.ComicSummary
import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import com.sekthdroid.compose.marvel.domain.SeriesList
import com.sekthdroid.compose.marvel.domain.SeriesSummary
import com.sekthdroid.compose.marvel.domain.StoriesList
import com.sekthdroid.compose.marvel.domain.StorySummary
import com.sekthdroid.compose.marvel.domain.Thumbnail

fun MarvelCharacter.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        description = description
    )
}

fun Thumbnail.toEntity(parentId: Long) = ThumbnailEntity(
    url = path,
    extension = extension,
    characterId = parentId
)

fun MarvelCharacter.toResourcesEntity(): List<ResourceEntity> {
    return listOf(
        comics.items.map {
            ResourceEntity(
                resourceUri = it.resourceURI,
                name = it.name,
                characterId = id,
                type = ResourceType.Comic
            )
        },
        series.items.map {
            ResourceEntity(
                resourceUri = it.resourceURI,
                name = it.name,
                characterId = id,
                type = ResourceType.Serie
            )
        },
        stories.items.map {
            ResourceEntity(
                resourceUri = it.resourceURI,
                name = it.name,
                characterId = id,
                type = ResourceType.Story
            )
        }
    ).flatten()
}

fun CompleteCharacter.toMarvelCharacter(): MarvelCharacter {
    return MarvelCharacter(
        id = character.id,
        name = character.name.orEmpty(),
        description = character.description.orEmpty(),
        thumbnail = Thumbnail(
            path = thumbnail?.url.orEmpty(),
            extension = thumbnail?.extension.orEmpty()
        ),
        comics = resources.toComicList(),
        series = resources.toSeriesList(),
        stories = resources.toStoriesList()
    )
}

fun List<ResourceEntity>.toComicList(): ComicList {
    return filter { it.type == ResourceType.Comic }
        .map {
            ComicSummary(
                it.resourceUri,
                it.name.orEmpty()
            )
        }
        .run { ComicList(size, this) }
}

fun List<ResourceEntity>.toSeriesList(): SeriesList {
    return filter { it.type == ResourceType.Story }
        .map {
            SeriesSummary(
                it.resourceUri,
                it.name.orEmpty()
            )
        }
        .run { SeriesList(size, this) }
}

fun List<ResourceEntity>.toStoriesList(): StoriesList {
    return filter { it.type == ResourceType.Serie }
        .map {
            StorySummary(
                it.resourceUri,
                it.name.orEmpty()
            )
        }
        .run { StoriesList(size, this) }
}