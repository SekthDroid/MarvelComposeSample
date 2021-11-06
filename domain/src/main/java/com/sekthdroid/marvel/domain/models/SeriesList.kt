package com.sekthdroid.marvel.domain.models

data class SeriesList(
    val available: Int,
    val items: List<SeriesSummary> = emptyList()
)
