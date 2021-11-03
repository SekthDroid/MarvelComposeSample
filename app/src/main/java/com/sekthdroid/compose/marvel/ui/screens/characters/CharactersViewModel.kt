package com.sekthdroid.compose.marvel.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sekthdroid.compose.marvel.data.CharactersRepository
import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val marvelRepository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CharactersState().toLoading())

    val state: StateFlow<CharactersState>
        get() = _state

    init {
        loadCharacters()
    }

    fun load() {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            marvelRepository.getCharacters()
                .onStart { _state.value = _state.value.toLoading() }
                .catch { _state.value = _state.value.toError() }
                .collect {
                    _state.value = _state.value.toData(it)
                }
        }
    }
}

data class CharactersState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val data: List<MarvelCharacter> = emptyList()
) {
    fun toError(): CharactersState {
        return copy(
            hasError = true,
            isLoading = false
        )
    }

    fun toLoading(): CharactersState {
        return copy(isLoading = true, hasError = false)
    }

    fun toData(data: List<MarvelCharacter>): CharactersState {
        return copy(
            isLoading = false,
            hasError = false,
            data = data
        )
    }
}
