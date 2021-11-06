package com.sekthdroid.marvel.data.api

import com.sekthdroid.marvel.domain.models.ComicList
import com.sekthdroid.marvel.domain.models.ComicSummary
import com.sekthdroid.marvel.domain.models.MarvelCharacter
import com.sekthdroid.marvel.domain.models.Page
import com.sekthdroid.marvel.domain.models.SeriesList
import com.sekthdroid.marvel.domain.models.SeriesSummary
import com.sekthdroid.marvel.domain.models.StoriesList
import com.sekthdroid.marvel.domain.models.StorySummary

internal fun <T, O> ResponsePage<T>.toPage(itemMapper: (T) -> O): Page<O> {
    return Page(offset, limit, total, count, results.map(itemMapper))
}

internal fun CharacterApiModel.toCharacter(): MarvelCharacter = with(this) {
    return MarvelCharacter(
        id,
        name,
        description,
        thumbnail.sourceUrl(),
        comics.toComicList(),
        series.toSeries(),
        stories.toStories()
    )
}

internal fun ResourceListApiModel<ComicSummaryApiModel>.toComicList(): ComicList {
    return ComicList(
        available,
        items.map { ComicSummary(it.resourceURI, it.name) }
    )
}

internal fun ResourceListApiModel<StorySummaryApiModel>.toStories(): StoriesList {
    return StoriesList(
        available,
        items.map { StorySummary(it.resourceURI, it.name) }
    )
}

internal fun ResourceListApiModel<SeriesSummaryApiModel>.toSeries(): SeriesList {
    return SeriesList(
        available,
        items.map { SeriesSummary(it.resourceURI, it.name) }
    )
}