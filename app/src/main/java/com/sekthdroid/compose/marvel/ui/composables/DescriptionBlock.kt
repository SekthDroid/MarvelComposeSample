package com.sekthdroid.compose.marvel.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sekthdroid.compose.marvel.ui.theme.MarvelFontFamily

@Composable
fun DescriptionBlock(description: String = "Hello this is a description") {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "Description",
            fontStyle = FontStyle.Normal,
            style = MaterialTheme.typography.h5,
            color = Color.White,
            fontFamily = MarvelFontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description.ifEmpty { "No description available" },
            style = MaterialTheme.typography.body1,
            color = Color.LightGray
        )
    }
}
