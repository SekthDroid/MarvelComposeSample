package com.sekthdroid.domain.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform