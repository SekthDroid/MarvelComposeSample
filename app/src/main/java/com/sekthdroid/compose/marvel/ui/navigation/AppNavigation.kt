package com.sekthdroid.compose.marvel.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sekthdroid.compose.marvel.ui.screens.Screen
import com.sekthdroid.compose.marvel.ui.screens.character.CharacterScreen
import com.sekthdroid.compose.marvel.ui.screens.characters.CharactersScreen
import com.sekthdroid.compose.marvel.ui.screens.comics.ComicScreen
import com.sekthdroid.compose.marvel.ui.screens.composable

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.CharactersList.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.CharactersList) {
            CharactersScreen(
                viewModel = hiltViewModel(),
                onCharacterClicked = {
                    navController.navigate(Screen.CharacterDetail.createRoute(it.id))
                }
            )
        }
        composable(Screen.CharacterDetail) {
            CharacterScreen(
                navigationController = navController,
                characterId = it.arguments?.getString("characterId"),
                viewModel = hiltViewModel()
            )
        }
        composable(Screen.ComicDetail) {
            ComicScreen(
                navigationController = navController,
                comicId = it.arguments?.getString("comicId"),
                viewModel = hiltViewModel()
            )
        }
    }
}
