package com.sekthdroid.compose.marvel.ui.screens.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sekthdroid.marvel.domain.characters.CharactersRepository
import com.sekthdroid.marvel.domain.models.MarvelCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MarvelCharacter?>(null)
    val state: StateFlow<MarvelCharacter?> = _state

    init {
        println(savedStateHandle.keys())
        viewModelScope.launch {
            repository.getCharacter(savedStateHandle.get("characterId"))
                .also {
                    _state.value = it
                }
        }
    }
}
