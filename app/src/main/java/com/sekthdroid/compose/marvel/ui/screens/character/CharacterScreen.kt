package com.sekthdroid.compose.marvel.ui.screens.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.sekthdroid.compose.marvel.ui.composables.CharacterHeader
import com.sekthdroid.compose.marvel.ui.providers.SingleCharacterProvider
import com.sekthdroid.compose.marvel.ui.screens.Screen
import com.sekthdroid.compose.marvel.ui.theme.MarvelFontFamily
import com.sekthdroid.compose.marvel.ui.theme.MarvelRed
import com.sekthdroid.marvel.domain.models.CharacterCollection
import com.sekthdroid.marvel.domain.models.MarvelCharacter
import com.sekthdroid.marvel.domain.models.toCharacterCollection

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
                onBackPressed = { navigationController.popBackStack() },
                onResourceClicked = { collection ->
                    navigationController.navigate(
                        Screen.ComicDetail.createRoute(collection.id)
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun CharacterDetail(
    @PreviewParameter(SingleCharacterProvider::class) character: MarvelCharacter,
    onBackPressed: () -> Unit = {},
    onResourceClicked: (CharacterCollection) -> Unit = {}
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

            item {
                DescriptionBlock(character.description)
            }

            // FIXME: 4/11/21 Maybe we could create some kind of method to create
            //  a section and remove duplicated blocks

            // Render comics section
            item {
                TitleDropdown(
                    title = quantifiedTitle(
                        resourceWithQuantity = R.string.comics_quantity,
                        resourceWithoutQuantity = R.string.comics,
                        quantity = character.comics.available
                    ),
                    expanded = comicSectionExpanded,
                    onTitleClick = {
                        comicSectionExpanded = it
                    }
                )
            }

            if (comicSectionExpanded) {
                items(character.comics.items) {
                    ResourceItem(it.name) {
                        onResourceClicked(it.toCharacterCollection())
                    }
                }
            }

            // Render stories section
            item {
                TitleDropdown(
                    title = quantifiedTitle(
                        resourceWithQuantity = R.string.stories_quantity,
                        resourceWithoutQuantity = R.string.stories,
                        quantity = character.stories.available
                    ),
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
                    title = quantifiedTitle(
                        resourceWithQuantity = R.string.series_quantity,
                        resourceWithoutQuantity = R.string.series,
                        character.series.available
                    ),
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

@Composable
fun quantifiedTitle(
    resourceWithQuantity: Int,
    resourceWithoutQuantity: Int,
    quantity: Int = 0,
    withQuantity: Boolean = true
): String {
    return if (withQuantity) {
        stringResource(id = resourceWithQuantity, quantity)
    } else {
        stringResource(id = resourceWithoutQuantity)
    }
}

@Preview
@Composable
fun DescriptionBlock(description: String = "Hello this is a description") {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = stringResource(R.string.description),
            fontStyle = FontStyle.Normal,
            style = MaterialTheme.typography.h5,
            color = Color.White,
            fontFamily = MarvelFontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description.ifEmpty { stringResource(R.string.description_empty_content) },
            style = MaterialTheme.typography.body1,
            color = Color.LightGray
        )
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
fun ResourceItem(value: String = "Sample", onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
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