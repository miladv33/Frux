package com.example.frux.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.frux.data.model.Hit
import com.example.frux.ui.theme.Shapes

@Composable
fun ButtonSheet(hit: Hit) {
    val downloadIcon = painterResource(id = com.example.frux.R.drawable.ic__download)
    val commentIcon = painterResource(id = com.example.frux.R.drawable.ic_comment)

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(Shapes.medium)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = hit.largeImageURL),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(
                    Shapes.medium
                )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                UserImage(hit = hit)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = hit.user,
                    style = MaterialTheme.typography.h5
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = hit.tags,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = hit.likes.toString())
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = downloadIcon,
                    contentDescription = null,
                    tint = Color.Blue,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = hit.downloads.toString())
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = commentIcon,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = hit.comments.toString())
            }
        }
    }

}
