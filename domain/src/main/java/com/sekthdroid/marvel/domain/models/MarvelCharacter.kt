package com.sekthdroid.marvel.domain.models

data class MarvelCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: String? = null,
    val comics: ComicList,
    val series: SeriesList,
    val stories: StoriesList
)