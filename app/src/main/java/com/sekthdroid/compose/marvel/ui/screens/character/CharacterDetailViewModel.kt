package com.sekthdroid.compose.marvel.ui.screens.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sekthdroid.compose.marvel.data.CharactersRepository
import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MarvelCharacter?>(null)
    val state: StateFlow<MarvelCharacter?> = _state

    fun fetch(id: String?) {
        viewModelScope.launch {
            repository.getCharacter(id)
                .also { _state.value = it }
        }
    }
}
