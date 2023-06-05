package com.sekthdroid.domain.shared.models

data class SeriesList(
    val available: Int,
    val items: List<SeriesSummary> = emptyList()
)
