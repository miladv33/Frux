package com.example.frux.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.frux.data.model.Hit
import com.example.frux.data.remote.PHOTO
import com.example.frux.presentation.PixabayViewModel

@Composable
fun Home(pixabayViewModel: PixabayViewModel = hiltViewModel()) {
    pixabayViewModel.searchImage("cat", PHOTO)
    val images = pixabayViewModel.pixabayImageLiveData.observeAsState()
    images.value?.hits?.let { ImageGridList(it) }
}


@Composable
fun ImageGridList(imageUrls: List<Hit>) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(imageUrls.size) { index ->
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrls[index].previewURL),
                        contentDescription = "Image from URL",
                        modifier = Modifier
                            .size(150.dp)
                    )
                }
            }
        }
    }
}
