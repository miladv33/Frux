package com.example.frux.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.frux.data.model.Hit
import com.example.frux.data.remote.Type
import com.example.frux.presentation.PixabayViewModel
import com.example.frux.ui.ArcRotationAnimation
import com.example.frux.ui.ModalBottomSheetDemo
import kotlinx.coroutines.*

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun Home(pixabayViewModel: PixabayViewModel = hiltViewModel()) {
    // Launch a coroutine to call searchImage only once
    val keyboardController = LocalSoftwareKeyboardController.current
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)

    ModalBottomSheetDemo(bottomSheetState, {
        pixabayViewModel.selectedImage.value?.let {
            ButtonSheet(it)
        }.run {
            Box(modifier = Modifier.height(1.dp))
        }
    }) {
        SearchPage(keyboardController, pixabayViewModel, bottomSheetState)
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
private fun SearchPage(
    keyboardController: SoftwareKeyboardController?,
    pixabayViewModel: PixabayViewModel,
    bottomSheetState: ModalBottomSheetState
) {
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        bottomSheetState.hide()
    }
    val images = pixabayViewModel.pixabayImageLiveData.observeAsState()

    LaunchedEffect(Unit) {
        pixabayViewModel.searchImage("fruits", Type.PHOTO.type)
    }

    Column {
        SearchInput {
            keyboardController?.hide()
            pixabayViewModel.searchImage(it, Type.PHOTO.type)
        }
        Spacer(modifier = Modifier.height(8.dp))
        images.value?.hits?.let {
            ImageGridList(it) { hit ->
                pixabayViewModel.setSelectedImage(hit)
                coroutineScope.launch {
                    bottomSheetState.show()
                }
            }
        }.run {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ArcRotationAnimation()
            }
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
            singleLine = true,
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

@Composable
fun ImageGridList(imageUrls: List<Hit>, onClick: (previewURL: Hit) -> Unit) {
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
                imageItem(imageUrls[index]) {
                    onClick.invoke(it)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun imageItem(
    hit: Hit,
    onClick: (previewURL: Hit) -> Unit
) {
    Card(
        modifier = Modifier.size(150.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = { onClick.invoke(hit) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = hit.previewURL),
            contentDescription = "Image from URL",
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)) // 16 dp corner radius
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

    }
}
