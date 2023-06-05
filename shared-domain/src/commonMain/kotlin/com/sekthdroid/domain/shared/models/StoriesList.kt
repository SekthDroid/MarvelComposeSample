package com.sekthdroid.domain.shared.models

data class StoriesList(
    val available: Int,
    val items: List<StorySummary> = emptyList()
)
