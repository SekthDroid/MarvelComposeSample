package com.sekthdroid.compose.marvel.data.sources.api

import com.sekthdroid.compose.marvel.domain.ComicList
import com.sekthdroid.compose.marvel.domain.ComicSummary
import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import com.sekthdroid.compose.marvel.domain.Page
import com.sekthdroid.compose.marvel.domain.SeriesList
import com.sekthdroid.compose.marvel.domain.SeriesSummary
import com.sekthdroid.compose.marvel.domain.StoriesList
import com.sekthdroid.compose.marvel.domain.StorySummary

fun <T, O> ResponsePage<T>.toPage(itemMapper: (T) -> O): Page<O> {
    return Page(offset, limit, total, count, results.map(itemMapper))
}

fun CharacterApiModel.toCharacter(): MarvelCharacter = with(this) {
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

fun ResourceListApiModel<ComicSummaryApiModel>.toComicList(): ComicList {
    return ComicList(
        available,
        items.map { ComicSummary(it.resourceURI, it.name) }
    )
}

fun ResourceListApiModel<StorySummaryApiModel>.toStories(): StoriesList {
    return StoriesList(
        available,
        items.map { StorySummary(it.resourceURI, it.name) }
    )
}

fun ResourceListApiModel<SeriesSummaryApiModel>.toSeries(): SeriesList {
    return SeriesList(
        available,
        items.map { SeriesSummary(it.resourceURI, it.name) }
    )
}