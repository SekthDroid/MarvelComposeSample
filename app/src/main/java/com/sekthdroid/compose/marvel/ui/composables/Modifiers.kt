package com.sekthdroid.compose.marvel.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.attachClickable(func: (() -> Unit)?): Modifier {
    return func?.let { clickable { it() } } ?: this
}

fun Brush.Companion.bottomToTopGradient(colors: List<Color>): Brush {
    return verticalGradient(
        colors,
        startY = Float.POSITIVE_INFINITY,
        endY = 0f
    )
}