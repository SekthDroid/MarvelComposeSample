package com.sekthdroid.compose.marvel

import android.app.Application
import com.sekthdroid.compose.marvel.ui.screens.character.CharacterModule
import com.sekthdroid.compose.marvel.ui.screens.characters.CharactersModule
import com.sekthdroid.marvel.data.di.DataModule
import com.sekthdroid.marvel.data.di.DatabaseModule
import com.sekthdroid.marvel.data.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelApplication)
            modules(
                DataModule,
                NetworkModule,
                DatabaseModule,
                CharactersModule,
                CharacterModule
            )
        }
    }
}