package com.sekthdroid.compose.marvel.ui.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sekthdroid.compose.marvel.domain.ComicList
import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import com.sekthdroid.compose.marvel.domain.SeriesList
import com.sekthdroid.compose.marvel.domain.StoriesList

object FakeCharacters {
    val characters = listOf("Spiderman", "Iron Man", "Thanos")
        .mapIndexed { index, characterName ->
            MarvelCharacter(
                index.toLong(),
                characterName,
                "Amazing Spiderman",
                "",
                ComicList(0),
                SeriesList(0),
                StoriesList(0)
            )
        }
}

class CharacterProvider : PreviewParameterProvider<MarvelCharacter> {
    override val values: Sequence<MarvelCharacter>
        get() = sequenceOf(FakeCharacters.characters.first())
}

class FakeCharactersProvider : PreviewParameterProvider<List<MarvelCharacter>> {
    override val values: Sequence<List<MarvelCharacter>>
        get() = sequenceOf(FakeCharacters.characters)
}
