package com.example.frux.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.frux.data.remote.PHOTO
import com.example.frux.presentation.PixabayViewModel

@Preview
@Composable
fun Home(pixabayViewModel: PixabayViewModel = hiltViewModel()) {
    pixabayViewModel.searchImage("cat", PHOTO)
    val images = pixabayViewModel.pixabayImageLiveData.observeAsState()
    println(images)
}