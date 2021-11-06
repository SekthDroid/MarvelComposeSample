package com.sekthdroid.compose.marvel.data.sources.room

import com.sekthdroid.marvel.domain.models.ComicList
import com.sekthdroid.marvel.domain.models.ComicSummary
import com.sekthdroid.marvel.domain.models.MarvelCharacter
import com.sekthdroid.marvel.domain.models.SeriesList
import com.sekthdroid.marvel.domain.models.SeriesSummary
import com.sekthdroid.marvel.domain.models.StoriesList
import com.sekthdroid.marvel.domain.models.StorySummary

fun MarvelCharacter.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        thumbnail = thumbnail,
        description = description
    )
}

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
        thumbnail = character.thumbnail,
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