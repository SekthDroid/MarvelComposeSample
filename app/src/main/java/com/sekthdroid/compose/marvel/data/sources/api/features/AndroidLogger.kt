package com.sekthdroid.compose.marvel.data.sources.api.features

import android.util.Log
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import javax.inject.Inject

class AndroidLogger @Inject constructor() : Logger {
    override fun log(message: String) {
        Log.i(Logging.key.name, message)
    }
}