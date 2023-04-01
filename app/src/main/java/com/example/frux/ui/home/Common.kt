package com.example.frux.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.frux.data.model.Hit

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