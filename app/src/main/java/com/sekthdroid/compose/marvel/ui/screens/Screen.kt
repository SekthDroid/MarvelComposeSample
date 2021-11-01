package com.sekthdroid.compose.marvel.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object CharactersList : Screen("characters")
    object CharacterDetail : Screen("character/{characterId}") {
        fun createRoute(characterId: Long) = route.replace("{characterId}", characterId.toString())
    }
}

fun NavGraphBuilder.composable(
    screen: Screen,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLink: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(screen.route, arguments, deepLink, content)
}