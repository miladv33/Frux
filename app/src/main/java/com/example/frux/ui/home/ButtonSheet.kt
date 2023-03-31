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
        // Display the views in the top right corner
        Text(
            text = hit.views.toString(),
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .background(Color.Black, shape = CircleShape)
        )
        // Display the user image in the top left corner
        val userPainter = rememberAsyncImagePainter(hit.userImageURL)
        Image(
            painter = userPainter,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.TopStart)
                .padding(8.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, shape = CircleShape)
        )
        // Display the preview URL as a clickable link in the bottom left corner
        val context = LocalContext.current
        Text(
            text = hit.previewURL,
            color = Color.Blue,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
                .clickable {
                    // Open the preview URL in a browser
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(hit.largeImageURL))
                    context.startActivity(intent)
                }
        )
        // Display the likes in the bottom right corner
        Text(
            text = hit.likes.toString(),
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .background(Color.Red, shape = CircleShape)
        )
    }
}
