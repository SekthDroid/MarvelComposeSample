package com.sekthdroid.marvel.data.api

import com.sekthdroid.domain.shared.models.ComicList
import com.sekthdroid.domain.shared.models.ComicSummary
import com.sekthdroid.domain.shared.models.MarvelCharacter
import com.sekthdroid.domain.shared.models.Page
import com.sekthdroid.domain.shared.models.SeriesList
import com.sekthdroid.domain.shared.models.SeriesSummary
import com.sekthdroid.domain.shared.models.StoriesList
import com.sekthdroid.domain.shared.models.StorySummary

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