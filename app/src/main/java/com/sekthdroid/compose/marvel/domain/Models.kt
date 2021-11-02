package com.sekthdroid.compose.marvel.domain

data class Page<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)

data class MarvelCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: String? = null,
    val comics: ComicList,
    val series: SeriesList,
    val stories: StoriesList
)

data class ComicList(
    val available: Int,
    val items: List<ComicSummary> = emptyList()
)

data class ComicSummary(
    val resourceURI: String,
    val name: String
)

data class SeriesList(
    val available: Int,
    val items: List<SeriesSummary> = emptyList()
)

data class SeriesSummary(
    val resourceURI: String,
    val name: String
)

data class StoriesList(
    val available: Int,
    val items: List<StorySummary> = emptyList()
)

data class StorySummary(
    val resourceURI: String,
    val name: String
)
