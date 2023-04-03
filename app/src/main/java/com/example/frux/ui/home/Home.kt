package com.example.frux.ui.home

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.frux.data.model.Hit
import com.example.frux.data.remote.Type
import com.example.frux.presentation.PixabayViewModel
import com.example.frux.ui.ArcRotationAnimation
import com.example.frux.ui.ModalBottomSheetDemo
import com.example.frux.ui.theme.*
import kotlinx.coroutines.*

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun Home(pixabayViewModel: PixabayViewModel = hiltViewModel()) {
    // Launch a coroutine to call searchImage only once
    val keyboardController = LocalSoftwareKeyboardController.current
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetDemo(bottomSheetState, {
        pixabayViewModel.selectedImage.value?.let {
            ButtonSheet(it)
        }.run {
            Box(modifier = Modifier.height(borderSize))
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
    val dialogueState = remember {
        mutableStateOf(false)
    }

    val selectedHit = remember {
        mutableStateOf<Hit?>(null)
    }
    val showButtonSheet = remember {
        mutableStateOf(false)
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
        Spacer(modifier = Modifier.height(defaultSpacing))
        images.value?.hits?.let {
            ImageColumnList(it) { hit ->
                selectedHit.value = hit
                dialogueState.value = true
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
    if (dialogueState.value) {
        showButtonSheet.value = false
        ShowMoreDetailsDialog(onYesClicked = {
            dialogueState.value = false
            showButtonSheet.value = true
        }) {
            dialogueState.value = false
            showButtonSheet.value = false
        }
    }

    if (showButtonSheet.value) {
        selectedHit.value?.let { pixabayViewModel.setSelectedImage(it) }
        coroutineScope.launch {
            bottomSheetState.show()
        }
    }
    LaunchedEffect(bottomSheetState) {
        snapshotFlow { bottomSheetState.isVisible }.collect { isVisible ->
            showButtonSheet.value = isVisible
        }
    }
}

@Composable
fun SearchInput(
    onSearchClick: (value: String) -> Unit
) {
    // Define a shape for the input text with rounded corners
    val shape = Shapes.medium

    // Define and remember a mutable state for the search value
    val searchValue = remember { mutableStateOf("") }

    // The rest of the code is unchanged
    val modifier = Modifier
        .padding(defaultSpacing)
        .background(MaterialTheme.colors.surface, shape)

    Row(
        modifier = modifier.border(
                width = borderSize, color = Color.White, shape = shape
            ), verticalAlignment = Alignment.CenterVertically
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
            }, modifier = Modifier.size(defaultIconButtonPadding)
        ) {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "Search"
            )
        }
    }
}

@Composable
fun ImageColumnList(imageUrls: List<Hit>, onClick: (previewURL: Hit) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            contentPadding = PaddingValues(defaultPadding), modifier = Modifier.fillMaxSize()
        ) {
            items(imageUrls.size) { index ->
                ImageItem(imageUrls[index]) {
                    onClick.invoke(it)
                }
            }
        }
    }
}

@Composable
private fun ImageItem(
    hit: Hit, onClick: (previewURL: Hit) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(loadingHeight)
        .clickable { onClick.invoke(hit) }
        .padding(defaultSpacing), elevation = cardCornerRadius, shape = Shapes.medium) {
        Row(
            modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.padding(defaultSpacing)) {
                val rememberAsyncImagePainter = rememberAsyncImagePainter(model = hit.previewURL)
                if (rememberAsyncImagePainter.state is AsyncImagePainter.State.Loading) {
                    LoadingImage()
                }
                Image(
                    painter = rememberAsyncImagePainter,
                    contentDescription = "Image from URL",
                    modifier = Modifier
                        .width(loadingImageSize)
                        .height(loadingImageSize)
                        .clip(Shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(start = defaultSpacing),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = hit.user,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hit.tags,
                    style = MaterialTheme.typography.caption,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
