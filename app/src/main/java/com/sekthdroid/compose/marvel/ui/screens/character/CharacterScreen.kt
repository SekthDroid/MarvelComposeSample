package com.sekthdroid.compose.marvel.ui.screens.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sekthdroid.compose.marvel.R
import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import com.sekthdroid.compose.marvel.ui.composables.CharacterHeader
import com.sekthdroid.compose.marvel.ui.providers.SingleCharacterProvider
import com.sekthdroid.compose.marvel.ui.theme.MarvelFontFamily
import com.sekthdroid.compose.marvel.ui.theme.MarvelRed

@Composable
fun CharacterScreen(
    navigationController: NavController,
    characterId: String?,
    viewModel: CharacterDetailViewModel = viewModel(),
) {

    LaunchedEffect(characterId) {
        // FIXME: 31/10/21 This should be triggered on ViewModel.init, lets handle it later
        viewModel.fetch(characterId)
    }

    val character by viewModel.state.collectAsState()

    Scaffold(backgroundColor = Color.Black) {
        character?.let {
            CharacterDetail(
                character = it,
                onBackPressed = { navigationController.popBackStack() }
            )
        }
    }
}

@Preview
@Composable
fun CharacterDetail(
    @PreviewParameter(SingleCharacterProvider::class) character: MarvelCharacter,
    onBackPressed: () -> Unit = {}
) {
    var comicSectionExpanded by remember { mutableStateOf(false) }
    var seriesSectionExpanded by remember { mutableStateOf(false) }
    var storiesSectionExpanded by remember { mutableStateOf(false) }

    Box {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier.zIndex(1f)
        ) {
            Image(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.clickable { onBackPressed() }
            )
        }
        LazyColumn {
            item {
                CharacterHeader(
                    characterName = character.name,
                    characterImage = character.thumbnail,
                    shape = RectangleShape,
                    height = 300.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = .4f),
                            Color.Black.copy(.4f)
                        )
                    )
                )
                Divider(color = MarvelRed)
            }

            // FIXME: 4/11/21 Maybe we could create some kind of method to create
            //  a section and remove duplicated blocks

            // Render comics section
            item {
                TitleDropdown(
                    title = stringResource(id = R.string.comics),
                    expanded = comicSectionExpanded,
                    onTitleClick = {
                        comicSectionExpanded = it
                    }
                )
            }

            if (comicSectionExpanded) {
                items(character.comics.items) {
                    ResourceItem(it.name)
                }
            }

            // Render stories section
            item {
                TitleDropdown(
                    title = stringResource(id = R.string.stories),
                    expanded = storiesSectionExpanded,
                    onTitleClick = {
                        storiesSectionExpanded = it
                    }
                )
            }

            if (storiesSectionExpanded) {
                items(character.stories.items) {
                    ResourceItem(it.name)
                }
            }

            // Render series section
            item {
                TitleDropdown(
                    title = stringResource(id = R.string.series),
                    expanded = seriesSectionExpanded,
                    onTitleClick = {
                        seriesSectionExpanded = it
                    }
                )
            }

            if (seriesSectionExpanded) {
                items(character.series.items) {
                    ResourceItem(it.name)
                }
            }
        }
    }
}

@Preview
@Composable
fun TitleDropdown(
    title: String = "Title",
    expanded: Boolean = false,
    onTitleClick: (Boolean) -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .heightIn(48.dp)
            .clickable { onTitleClick(expanded.not()) }
            .padding(8.dp)
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
}

@Preview(widthDp = 320)
@Composable
fun ResourceItem(value: String = "Sample") {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(36.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = value,
            color = Color.LightGray,
            style = MaterialTheme.typography.body1,
            fontFamily = MarvelFontFamily,
            textAlign = TextAlign.Start
        )
    }
}