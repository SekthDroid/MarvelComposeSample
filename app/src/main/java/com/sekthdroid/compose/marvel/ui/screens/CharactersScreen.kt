package com.sekthdroid.compose.marvel.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.systemBarsPadding
import com.sekthdroid.compose.marvel.R
import com.sekthdroid.compose.marvel.data.CharactersRepository
import com.sekthdroid.compose.marvel.domain.MarvelCharacter
import com.sekthdroid.compose.marvel.ui.composables.ImagePlaceholder
import com.sekthdroid.compose.marvel.ui.providers.FakeCharactersProvider
import com.sekthdroid.compose.marvel.ui.theme.MarvelFontFamily
import com.sekthdroid.compose.marvel.ui.theme.MarvelRed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val marvelRepository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<MarvelCharacter>())

    val state: StateFlow<List<MarvelCharacter>>
        get() = _state

    init {
        viewModelScope.launch {
            marvelRepository.getCharacters()
                .collect {
                    _state.value = it
                }
        }
    }
}

enum class ContentType {
    List,
    Grid;

    fun inverse(): ContentType {
        return if (this == List) Grid else List
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = viewModel(),
    onCharacterClicked: (MarvelCharacter) -> Unit = {}
) {
    val characters by viewModel.state.collectAsState()

    // Here we use rememberSaveable so the view rememebr this state after coming back from detail
    val (contentType, setContentType) = rememberSaveable { mutableStateOf(ContentType.List) }

    Scaffold(
        topBar = {
            CharactersAppBar(contentType = contentType, onClick = setContentType)
        }
    ) {
        Column(
            Modifier.systemBarsPadding(true)
        ) {
            Crossfade(targetState = contentType, animationSpec = tween(300)) {
                when (it) {
                    ContentType.List -> {
                        CharactersList(characters = characters, onCharacterClicked)
                    }
                    ContentType.Grid -> {
                        CharactersGrid(characters = characters, onCharacterClicked)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LayoutTypeSelector(
    contentType: ContentType = ContentType.List,
    onClick: (ContentType) -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(34.dp)
            .height(34.dp)
            .clickable { onClick(contentType.inverse()) }
    ) {
        when (contentType) {
            ContentType.List -> {
                Icon(
                    painterResource(id = R.drawable.ic_baseline_grid_view_24),
                    contentDescription = "Show as Grid"
                )
            }
            ContentType.Grid -> {
                Icon(
                    painterResource(id = R.drawable.ic_baseline_list_24),
                    contentDescription = "Show as List"
                )
            }
        }
    }
}

@Preview
@Composable
fun CharactersAppBar(
    contentType: ContentType = ContentType.List,
    onClick: (ContentType) -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = "Characters", fontFamily = MarvelFontFamily) },
        actions = {
            LayoutTypeSelector(contentType) {
                onClick(it)
            }
        }
    )
}

@Preview
@Composable
fun CharactersList(
    @PreviewParameter(FakeCharactersProvider::class) characters: List<MarvelCharacter>,
    onCharacterClicked: (MarvelCharacter) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(characters) {
            CharacterListItem(
                name = it.name,
                image = it.thumbnail,
                onClick = { onCharacterClicked(it) },
                modifier = Modifier.height(250.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun CharactersGrid(
    @PreviewParameter(FakeCharactersProvider::class) characters: List<MarvelCharacter>,
    onCharacterClicked: (MarvelCharacter) -> Unit = {}
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(characters) {
            CharacterListItem(
                name = it.name,
                image = it.thumbnail,
                onClick = { onCharacterClicked(it) },
                modifier = Modifier
                    .height(250.dp)
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun CharacterListItem(
    modifier: Modifier = Modifier,
    name: String = "Spiderman",
    image: String? = null,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .then(modifier)
            .heightIn(300.dp)
            .border(width = 1.dp, Color.DarkGray, shape = RoundedCornerShape(4.dp))
            .clickable { onClick() }
    ) {
        ImagePlaceholder(
            imageUrl = image,
            modifier = Modifier
                .weight(0.75f)
                .clip(RoundedCornerShape(4.dp))
                .fillMaxWidth()
        )
        CharacterItemDivider()
        Text(
            text = name,
            color = Color.White,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Start,
            fontFamily = MarvelFontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp)
        )
    }
}

@Preview(widthDp = 150, heightDp = 300)
@Composable
fun CharacterGridItem(
    modifier: Modifier = Modifier,
    name: String = "SpiderMan",
    avatar: String = "",
    onClick: () -> Unit = { },
) {
    Column(
        modifier = Modifier
            .then(modifier)
            .padding(16.dp)
            .height(300.dp)
            .border(width = 1.dp, Color.DarkGray, shape = RoundedCornerShape(4.dp))
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberImagePainter(avatar),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(0.75f)
                .fillMaxWidth()
        )
        CharacterItemDivider()
        Text(
            text = name,
            color = Color.White,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Start,
            fontFamily = MarvelFontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp)
        )
    }
}

@Composable
fun CharacterItemDivider() {
    Divider(Modifier.height(4.dp), color = MarvelRed)
}