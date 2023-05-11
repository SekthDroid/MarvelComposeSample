package com.sekthdroid.marvel.data.sql

import com.sekthdroid.marvel.data.AppDatabase
import com.sekthdroid.marvel.data.Characters
import com.sekthdroid.marvel.data.CharactersQueries
import com.sekthdroid.marvel.data.Resources
import com.sekthdroid.marvel.data.ResourcesQueries
import com.sekthdroid.marvel.domain.models.ComicList
import com.sekthdroid.marvel.domain.models.ComicSummary
import com.sekthdroid.marvel.domain.models.MarvelCharacter
import com.sekthdroid.marvel.domain.models.SeriesList
import com.sekthdroid.marvel.domain.models.SeriesSummary
import com.sekthdroid.marvel.domain.models.StoriesList
import com.sekthdroid.marvel.domain.models.StorySummary
import javax.inject.Inject
import javax.inject.Singleton

typealias CharactersEntity = Characters

internal fun MarvelCharacter.toCharactersEntity() =
    CharactersEntity(id, name, thumbnail, description)

internal fun MarvelCharacter.toResourcesEntity(): List<Resources> {
    return listOf(
        comics.items.map {
            Resources(
                resourceUri = it.resourceURI,
                name = it.name,
                characterId = id,
                type = ResourceType.Comic
            )
        },
        series.items.map {
            Resources(
                resourceUri = it.resourceURI,
                name = it.name,
                characterId = id,
                type = ResourceType.Serie
            )
        },
        stories.items.map {
            Resources(
                resourceUri = it.resourceURI,
                name = it.name,
                characterId = id,
                type = ResourceType.Story
            )
        }
    ).flatten()
}

internal fun List<Resources>.toComicList(): ComicList {
    return filter { it.type == ResourceType.Comic }
        .map {
            ComicSummary(
                it.resourceUri,
                it.name.orEmpty()
            )
        }
        .run { ComicList(size, this) }
}

internal fun List<Resources>.toSeriesList(): SeriesList {
    return filter { it.type == ResourceType.Serie }
        .map {
            SeriesSummary(
                it.resourceUri,
                it.name.orEmpty()
            )
        }
        .run { SeriesList(size, this) }
}


internal fun List<Resources>.toStoriesList(): StoriesList {
    return filter { it.type == ResourceType.Story }
        .map {
            StorySummary(
                it.resourceUri,
                it.name.orEmpty()
            )
        }
        .run { StoriesList(size, this) }
}

@Singleton
class LocalDatasource @Inject constructor(private val database: AppDatabase) {
    private val resources: ResourcesQueries
        get() = database.resourcesQueries

    private val characters: CharactersQueries
        get() = database.charactersQueries

    suspend fun insert(vararg character: MarvelCharacter) {
        database.transaction {
            character.forEach { each ->
                characters.insertFullCharacterObject(each.toCharactersEntity())
                resources.deleteFromCharacter(each.id)
                each.toResourcesEntity().forEach { resource ->
                    resources.insertByCharacter(resource)
                }
            }
        }
    }

    suspend fun get(): List<MarvelCharacter> {
        return characters.selectAll().executeAsList()
            .map {
                val resources = resources.getByCharacter(it.id).executeAsList()

                MarvelCharacter(
                    it.id,
                    it.name.orEmpty(),
                    it.description.orEmpty(),
                    it.thumbnail.orEmpty(),
                    resources.toComicList(),
                    resources.toSeriesList(),
                    resources.toStoriesList(),
                )
            }
    }

    suspend fun get(id: Long): MarvelCharacter? {
        return characters.selectById(id).executeAsOneOrNull()?.let {
            val resources = resources.getByCharacter(it.id).executeAsList()
            MarvelCharacter(
                it.id,
                it.name.orEmpty(),
                it.description.orEmpty(),
                it.thumbnail.orEmpty(),
                resources.toComicList(),
                resources.toSeriesList(),
                resources.toStoriesList(),
            )
        }
    }
}