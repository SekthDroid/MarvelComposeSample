package com.sekthdroid.compose.marvel.ui.screens.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import com.sekthdroid.compose.marvel.ui.composables.CharacterHeader
import com.sekthdroid.compose.marvel.ui.composables.DescriptionBlock
import com.sekthdroid.compose.marvel.ui.composables.ResourceBlock
import com.sekthdroid.compose.marvel.ui.providers.FakeCharactersProvider
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

@Composable
fun CharacterDetail(
    @PreviewParameter(FakeCharactersProvider::class) character: MarvelCharacter,
    onBackPressed: () -> Unit = {}
) {
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
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
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
            DescriptionBlock(character.description)
            Spacer(modifier = Modifier.height(8.dp))
            ResourceBlock(
                title = "Comics",
                items = character.comics.items.map { it.name }
            )
            Spacer(modifier = Modifier.height(8.dp))
            ResourceBlock(
                title = "Series",
                items = character.series.items.map { it.name }
            )
            Spacer(modifier = Modifier.height(8.dp))
            ResourceBlock(
                title = "Stories",
                items = character.stories.items.map { it.name }
            )
        }
    }
}

@Composable
fun DefaultDivider() {
    Spacer(modifier = Modifier.height(8.dp))
}
