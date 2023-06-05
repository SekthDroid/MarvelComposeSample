package com.sekthdroid.marvel.data.api.plugins

import android.util.Log
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

internal class AndroidLogger : Logger {
    override fun log(message: String) {
        Log.i(Logging.key.name, message)
    }
}