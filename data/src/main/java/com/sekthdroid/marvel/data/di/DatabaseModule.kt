package com.sekthdroid.marvel.data.di

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.sekthdroid.marvel.data.AppDatabase
import com.sekthdroid.marvel.data.Resources
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DatabaseModule = module {
    single {
        AppDatabase.invoke(
            AndroidSqliteDriver(AppDatabase.Schema, androidContext(), "app_database.db"),
            Resources.Adapter(
                typeAdapter = EnumColumnAdapter()
            )
        )
    }
}