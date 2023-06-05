package com.sekthdroid.marvel.data.di

import com.sekthdroid.domain.shared.characters.CharactersRepository
import com.sekthdroid.marvel.data.DefaultCharactersRepository
import com.sekthdroid.marvel.data.api.NetworkDatasource
import com.sekthdroid.marvel.data.sql.LocalDatasource
import org.koin.dsl.module

val DataModule = module {
    factory {
        LocalDatasource(get())
    }

    factory {
        NetworkDatasource(get(), get())
    }

    single<CharactersRepository> {
        DefaultCharactersRepository(get(), get())
    }
}