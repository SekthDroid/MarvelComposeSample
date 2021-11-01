package com.sekthdroid.compose.marvel.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sekthdroid.compose.marvel.ui.theme.BlackGradient
import com.sekthdroid.compose.marvel.ui.theme.MarvelFontFamily
import com.sekthdroid.compose.marvel.ui.theme.MarvelRed

@Preview(heightDp = 200)
@Composable
fun CharacterHeader(
    characterName: String = "SpiderMan",
    characterImage: String? = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
    shape: Shape = RoundedCornerShape(8.dp),
    height: Dp = 200.dp,
    brush: Brush = Brush.bottomToTopGradient(BlackGradient),
    onClick: (() -> Unit)? = null,
) {

    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .clip(shape)
            .background(MarvelRed)
            .attachClickable(onClick)
    ) {
        ImagePlaceholder(
            imageUrl = characterImage,
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            brush = brush,
                            blendMode = BlendMode.Multiply
                        )
                    }
                }
        )
        Text(
            text = characterName,
            color = Color.White,
            fontFamily = MarvelFontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(8.dp)
        )
    }
}
