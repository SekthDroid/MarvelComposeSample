package com.sekthdroid.compose.marvel.ui.screens.comics

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor() : ViewModel() {

}

@Composable
fun ComicScreen(
    navigationController: NavController,
    comicId: String?,
    viewModel: ComicDetailViewModel = viewModel()
) {
    ComicDetail(comicId)
}

@Preview
@Composable
fun ComicDetail(comicId: String? = null) {
    Scaffold {
        Text(text = "Opened with $comicId")
    }
}