package com.example.frux.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.frux.R
import com.example.frux.data.enum.ImageLoading
import com.example.frux.data.model.Hit
import com.example.frux.ui.loading.SimpleArcRotation
import com.example.frux.ui.theme.*

@Composable
fun ButtonSheet(hit: Hit) {
    val downloadIcon = painterResource(id = R.drawable.ic__download)
    val commentIcon = painterResource(id = R.drawable.ic_comment)

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(Shapes.medium)
    ) {
        CustomImage(
            hit.largeImageURL,
            Modifier
                .fillMaxWidth()
                .height(cardImageHeight)
                .clip(
                    Shapes.medium
                )
        )
        Spacer(modifier = Modifier.height(defaultMargin))
        Column(
            modifier = Modifier.padding(defaultPadding)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                UserImage(hit = hit)
                Spacer(modifier = Modifier.width(defaultSpacing))
                Text(
                    text = hit.user,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.height(defaultMargin))
            Text(
                text = hit.tags,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(defaultMargin))
            Row {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = LikeColor,
                    modifier = Modifier.size(defaultIconSize)
                )
                Spacer(modifier = Modifier.width(defaultSpacing))
                Text(text = hit.likes.toString(), color = MaterialTheme.colors.primary)
                Spacer(modifier = Modifier.width(defaultMargin))
                Icon(
                    painter = downloadIcon,
                    contentDescription = null,
                    tint = DownloadColor,
                    modifier = Modifier.size(defaultIconSize)
                )
                Spacer(modifier = Modifier.width(defaultSpacing))
                Text(text = hit.downloads.toString(), color = MaterialTheme.colors.primary)
                Spacer(modifier = Modifier.width(defaultMargin))
                Icon(
                    painter = commentIcon,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier.size(defaultIconSize)
                )
                Spacer(modifier = Modifier.width(defaultSpacing))
                Text(text = hit.comments.toString(), color = MaterialTheme.colors.primary)
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
                .crossfade(true)
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
