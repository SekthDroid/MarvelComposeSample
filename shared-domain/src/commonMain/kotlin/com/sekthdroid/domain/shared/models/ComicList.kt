package com.sekthdroid.domain.shared.models

data class ComicList(
    val available: Int,
    val items: List<ComicSummary> = emptyList()
)