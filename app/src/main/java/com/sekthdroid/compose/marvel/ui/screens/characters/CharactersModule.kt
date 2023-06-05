package com.sekthdroid.compose.marvel.ui.screens.characters

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val CharactersModule = module {
    viewModel {
        CharactersViewModel(get())
    }
}