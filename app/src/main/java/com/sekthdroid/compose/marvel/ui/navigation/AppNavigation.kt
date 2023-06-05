package com.sekthdroid.compose.marvel.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sekthdroid.compose.marvel.ui.screens.Screen
import com.sekthdroid.compose.marvel.ui.screens.character.CharacterScreen
import com.sekthdroid.compose.marvel.ui.screens.characters.CharactersScreen
import com.sekthdroid.compose.marvel.ui.screens.composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.CharactersList.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.CharactersList) {
            CharactersScreen(
                viewModel = koinViewModel(),
                onCharacterClicked = {
                    navController.navigate(Screen.CharacterDetail.createRoute(it.id))
                }
            )
        }
        composable(Screen.CharacterDetail) {
            CharacterScreen(
                viewModel = koinViewModel(),
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}
