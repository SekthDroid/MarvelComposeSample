package com.sekthdroid.compose.marvel.data.sources.api

import kotlinx.serialization.Serializable

@Serializable
data class MarvelResponse<T>(
    val data: ResponsePage<T>
)

@Serializable
data class ResponsePage<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)

@Serializable
data class CharacterApiModel(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailApiModel,
    val comics: ResourceListApiModel<ComicSummaryApiModel>,
    val series: ResourceListApiModel<SeriesSummaryApiModel>,
    val stories: ResourceListApiModel<StorySummaryApiModel>
)

@Serializable
data class ThumbnailApiModel(val path: String, val extension: String) {
    fun sourceUrl() = "${path.toHttps()}.$extension"
}

@Serializable
data class ResourceListApiModel<T>(
    val available: Int,
    val collectionURI: String = "",
    val items: List<T> = emptyList()
)

@Serializable
data class ComicSummaryApiModel(
    val resourceURI: String,
    val name: String
)

@Serializable
data class SeriesSummaryApiModel(
    val resourceURI: String,
    val name: String
)

@Serializable
data class StorySummaryApiModel(
    val resourceURI: String,
    val name: String
)

fun String.toHttps() = if (startsWith("https"))
    this
else
    replace("http", "https")
