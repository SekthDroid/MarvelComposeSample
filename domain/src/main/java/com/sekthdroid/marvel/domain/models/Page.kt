package com.sekthdroid.marvel.domain.models

data class Page<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)