package com.example.frux.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.frux.data.model.Hit
import com.example.frux.data.remote.Type
import com.example.frux.presentation.PixabayViewModel

@Composable
fun Home(pixabayViewModel: PixabayViewModel = hiltViewModel()) {
    // Launch a coroutine to call searchImage only once
    LaunchedEffect(Unit) {
        pixabayViewModel.searchImage("cat", Type.PHOTO.type)
    }
    val images = pixabayViewModel.pixabayImageLiveData.observeAsState()
    Column {
        SearchInput {
            pixabayViewModel.searchImage(it,Type.PHOTO.type)
        }
        Spacer(modifier = Modifier.height(8.dp))
        images.value?.hits?.let {
            ImageGridList(it)
        }
    }
}

@Composable
fun SearchInput(
    onSearchClick: (value: String) -> Unit
) {
    // Define a shape for the input text with rounded corners
    val shape = RoundedCornerShape(8.dp)

    // Define and remember a mutable state for the search value
    val searchValue = remember { mutableStateOf("") }

    // The rest of the code is unchanged
    val modifier = Modifier
        .padding(8.dp)
        .background(MaterialTheme.colors.surface, shape)

    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.White,
                shape = shape
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchValue.value,
            onValueChange = {
                searchValue.value = it
            },
            modifier = Modifier.weight(1f),
            shape = shape,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )

        IconButton(
            onClick = {
                onSearchClick.invoke(searchValue.value)
            },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageGridList(imageUrls: List<Hit>) {
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
                imageItem(imageUrls[index].previewURL) {

                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun imageItem(
    previewURL: String,
    onClick: (previewURL: String) -> Unit
) {
    Card(
        modifier = Modifier.size(150.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = { onClick.invoke(previewURL) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = previewURL),
            contentDescription = "Image from URL",
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)) // 16 dp corner radius
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
