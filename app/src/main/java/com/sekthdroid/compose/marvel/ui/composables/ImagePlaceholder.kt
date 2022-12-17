package com.sekthdroid.compose.marvel.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sekthdroid.compose.marvel.ui.theme.MarvelFontFamily
import com.sekthdroid.compose.marvel.ui.theme.MarvelRed

fun String?.isImageAvailable(): Boolean {
    if (isNullOrEmpty()) return false
    return "image_not_available" in this
}

@Preview
@Composable
fun ImagePlaceholder(modifier: Modifier = Modifier, imageUrl: String? = null) {
    if (imageUrl.isImageAvailable()) {
        Box(
            modifier = Modifier
                .then(modifier)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "IMAGE NOT FOUND",
                color = Color.White,
                style = MaterialTheme.typography.h4,
                fontFamily = MarvelFontFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(MarvelRed)
                    .padding(8.dp)
            )
        }
    } else {
        AsyncImage(
            model = imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}