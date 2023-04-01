package com.example.frux.ui.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.frux.data.model.Hit

@Composable
fun ButtonSheet(hit: Hit) {
// Load the large image from the URL
    val painter = rememberAsyncImagePainter(hit.largeImageURL)
    // Create a modifier for the image size and shape
    val modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .clip(RoundedCornerShape(8.dp))

    // Create a box to contain the image and other elements
    Box(modifier = modifier) {
        // Display the image with content scale to fill the box
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier,
        )
        BlackBox(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp)
                .width(100.dp)
                .height(50.dp)
        ) {
            Text(
                text = hit.views.toString(),
                color = Color.White,
            )
        }
        // Display the user image in the top left corner
        Box(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            UserImage(hit)
        }
        val context = LocalContext.current
        BlackBox(modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(8.dp)
            .height(50.dp)
            .clickable {
                // Open the preview URL in a browser
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(hit.largeImageURL))
                context.startActivity(intent)
            }
        ) {
            Text(
                text = hit.previewURL,
                color = Color.Blue,
                maxLines = 1
            )
        }
        // Display the preview URL as a clickable link in the bottom left corner
        // Display the likes in the bottom right corner
        BlackBox(
            modifier = Modifier
                .height(50.dp)
                .width(80.dp)
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        ) {
            Text(
                text = hit.likes.toString(),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun BlackBox(modifier: Modifier, content: @Composable () -> Unit) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        // Create a modifier for the box size and shape
        val boxModifier = Modifier
            .size(90.dp)
            .clip(RoundedCornerShape(8.dp))
        // Create a box with a black background with 0.4 alpha
        Box(
            modifier = boxModifier.background(Color.Black.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            // Display the content inside the box
            content()
        }
    }

}