package com.sekthdroid.compose.marvel.ui.screens.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sekthdroid.domain.shared.characters.CharactersRepository
import com.sekthdroid.domain.shared.models.MarvelCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MarvelCharacter?>(null)
    val state: StateFlow<MarvelCharacter?> = _state

    init {
        viewModelScope.launch {
            repository.getCharacter(savedStateHandle["characterId"])
                .also {
                    _state.value = it
                }
        }
    }
}
