package com.sekthdroid.marvel.domain.models

data class ComicList(
    val available: Int,
    val items: List<ComicSummary> = emptyList()
)