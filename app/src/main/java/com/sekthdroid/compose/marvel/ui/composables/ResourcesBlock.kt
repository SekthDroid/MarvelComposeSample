package com.sekthdroid.compose.marvel.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sekthdroid.compose.marvel.ui.theme.MarvelFontFamily

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun ResourceBlock(title: String = "Comics", items: List<String> = emptyList()) {
    var expanded by remember { mutableStateOf(false) }
    var headerHeight by remember { mutableStateOf(0) }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .heightIn(48.dp)
            .clickable { expanded = expanded.not() }
            .padding(8.dp)
            .onSizeChanged {
                headerHeight = it.height
            }
    ) {
        Text(
            text = title,
            fontStyle = FontStyle.Normal,
            style = MaterialTheme.typography.h5,
            color = Color.White,
            fontFamily = MarvelFontFamily,
            modifier = Modifier.weight(1f)
        )

        val dropDown = if (!expanded) {
            Icons.Filled.KeyboardArrowDown
        } else {
            Icons.Filled.KeyboardArrowUp
        }
        Image(
            imageVector = dropDown,
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.LightGray)
        )
    }
    AnimatedVisibility(
        visible = expanded,
        enter = slideInVertically(
            initialOffsetY = { -headerHeight }
        ),
        exit = slideOutVertically(
            targetOffsetY = { -headerHeight }
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items.forEach {
                ResourceItem(it)
            }
        }
    }
}

@Composable
fun ResourceItem(value: String = "Sample") {
    Text(
        text = value,
        color = Color.LightGray,
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp),
        style = MaterialTheme.typography.body2
    )
}