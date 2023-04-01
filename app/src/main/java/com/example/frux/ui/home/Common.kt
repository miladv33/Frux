package com.example.frux.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.frux.data.model.Hit
import com.example.frux.ui.loading.SimpleArcRotation

@Composable
fun UserImage(hit: Hit) {
    val userPainter = rememberAsyncImagePainter(hit.userImageURL)
    Image(
        painter = userPainter,
        contentDescription = null,
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(CircleShape)
            .border(2.dp, Color.White, shape = CircleShape)
    )
}

@Preview
@Composable
fun LoadingImage() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Gray, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center,

        ) {
        SimpleArcRotation()
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

@Composable
fun ShowMoreDetailsDialog(onYesClicked: () -> Unit, onNoClicked: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onNoClicked() },
        title = { Text("Do you want to see more details?") },
        confirmButton = {
            Button(
                onClick = { onYesClicked() },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(
                onClick = { onNoClicked() },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text("No")
            }
        }

    )
}