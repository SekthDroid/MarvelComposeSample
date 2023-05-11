package com.sekthdroid.marvel.data.di

import android.content.Context
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.sekthdroid.marvel.data.AppDatabase
import com.sekthdroid.marvel.data.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Singleton
    @Provides
    fun appDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.invoke(
            AndroidSqliteDriver(AppDatabase.Schema, appContext, "app_database.db"),
            Resources.Adapter(
                typeAdapter = EnumColumnAdapter()
            )
        )
    }
}