package com.sekthdroid.marvel.domain.models

sealed class CharacterCollection(open val id: Long) {
    data class Comic(override val id: Long) : CharacterCollection(id)
    data class Story(override val id: Long) : CharacterCollection(id)
    data class Serie(override val id: Long) : CharacterCollection(id)
}

fun ComicSummary.toCharacterCollection() = CharacterCollection.Comic(resourceId().toLong())