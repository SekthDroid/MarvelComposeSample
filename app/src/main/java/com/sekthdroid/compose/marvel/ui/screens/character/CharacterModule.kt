package com.sekthdroid.compose.marvel.ui.screens.character

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val CharacterModule = module {
    viewModel {
        CharacterDetailViewModel(get(), get())
    }
}