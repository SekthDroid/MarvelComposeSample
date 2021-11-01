package com.sekthdroid.compose.marvel.domain

import kotlinx.serialization.Serializable

@Serializable
data class MarvelResponse(
    val data: MarvelPage
)

@Serializable
data class MarvelPage(
    val results: List<MarvelCharacter>
)

@Serializable
data class MarvelCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: ComicList,
    val series: SeriesList,
    val stories: StoriesList
)

@Serializable
data class Thumbnail(val path: String, val extension: String) {
    fun sourceUrl() = "${path.toHttps()}.$extension"
}

@Serializable
data class ComicList(
    val available: Int,
    val items: List<ComicSummary> = emptyList()
)

@Serializable
data class ComicSummary(
    val resourceURI: String,
    val name: String
)

@Serializable
data class SeriesList(
    val available: Int,
    val items: List<SeriesSummary> = emptyList()
)

@Serializable
data class SeriesSummary(
    val resourceURI: String,
    val name: String
)

@Serializable
data class StoriesList(
    val available: Int,
    val items: List<StorySummary> = emptyList()
)

@Serializable
data class StorySummary(
    val resourceURI: String,
    val name: String
)

fun String.toHttps() = if (startsWith("https"))
    this
else
    replace("http", "https")
