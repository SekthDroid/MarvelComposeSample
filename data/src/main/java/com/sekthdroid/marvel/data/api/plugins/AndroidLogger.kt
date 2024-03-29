package com.sekthdroid.marvel.data.api.plugins

import android.util.Log
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import javax.inject.Inject

internal class AndroidLogger @Inject constructor() : Logger {
    override fun log(message: String) {
        Log.i(Logging.key.name, message)
    }
}