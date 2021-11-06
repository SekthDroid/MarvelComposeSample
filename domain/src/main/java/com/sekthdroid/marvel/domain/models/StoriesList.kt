package com.sekthdroid.marvel.domain.models

data class StoriesList(
    val available: Int,
    val items: List<StorySummary> = emptyList()
)
