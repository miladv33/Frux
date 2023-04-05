package com.example.frux.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.frux.R
import com.example.frux.data.enum.ImageLoading
import com.example.frux.data.model.Hit
import com.example.frux.ui.loading.SimpleArcRotation
import com.example.frux.ui.theme.*

@Composable
fun UserImage(hit: Hit) {
    val userPainter = rememberAsyncImagePainter(hit.userImageURL)
    Image(
        painter = userPainter,
        contentDescription = null,
        modifier = Modifier
            .size(userImageSize)
            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colors.primary, shape = CircleShape)
    )
}

@Preview
@Composable
fun LoadingImage() {
    Box(
        modifier = Modifier
            .size(loadingImageSize)
            .background(MaterialTheme.colors.background, Shapes.medium),
        contentAlignment = Alignment.Center,

        ) {
        SimpleArcRotation()
    }
}


@Composable
fun ShowMoreDetailsDialog(onYesClicked: () -> Unit, onNoClicked: () -> Unit) {
    Dialog(
        onDismissRequest = { onNoClicked() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            shape = Shapes.large,
            color = Color.White,
            modifier = Modifier.padding(defaultSpacing)
        ) {
            Column(
                modifier = Modifier.width(dialogueWidth).padding(dialogueCornerRadius)
            ) {
                Text(
                    text = stringResource(R.string.dialogue_alert),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = TextColorWhiteBackground,
                    modifier = Modifier.padding(bottom = defaultSpacing)
                )
                Text(
                    text = stringResource(R.string.detail_alert),
                    fontSize = 14.sp,
                    color = TextColorWhiteBackground,
                    modifier = Modifier.padding(bottom = defaultPadding)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = { onNoClicked() },
                        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.secondaryVariant)
                    ) {
                        Text(stringResource(R.string.no), color = TextColorWhiteBackground)
                    }
                    TextButton(
                        onClick = { onYesClicked() },
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.White),
                        modifier = Modifier.padding(start = defaultSpacing)
                    ) {
                        Text(stringResource(R.string.yes), color = TextColorWhiteBackground)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomImage(
    imageUrl: String,
    modifier: Modifier
) {
    val brokenImage = painterResource(id = R.drawable.ic_image_broken)
    Box {
        val imageState = remember {
            mutableStateOf(ImageLoading.LOADING)
        }
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = modifier,
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(700)
                .build(),
            contentDescription = "",
            onLoading = {
                imageState.value = ImageLoading.LOADING
            },
            onError = {
                imageState.value = ImageLoading.ERROR
            },
            onSuccess = {
                imageState.value = ImageLoading.SUCCESS
            }
        )
        if (imageState.value == ImageLoading.LOADING) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardImageHeight)
            ) {
                SimpleArcRotation()
            }
        }
        if (imageState.value == ImageLoading.ERROR) {
            Image(
                painter = brokenImage, contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardImageHeight)
                    .clip(
                        Shapes.medium
                    )
            )
        }
    }
}