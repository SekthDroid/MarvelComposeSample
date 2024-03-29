package com.sekthdroid.compose.marvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sekthdroid.compose.marvel.ui.navigation.AppNavigation
import com.sekthdroid.compose.marvel.ui.theme.MarvelComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MarvelComposeTheme(true) {

                val systemUiController = rememberSystemUiController()
                DisposableEffect(systemUiController) {
                    systemUiController.setStatusBarColor(
                        Color.Transparent
                    )
                    onDispose {  }
                }

                AppNavigation(navController = rememberNavController())
            }
        }
    }
}