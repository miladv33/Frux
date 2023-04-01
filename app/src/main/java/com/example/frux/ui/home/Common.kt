package com.example.frux.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.example.frux.R
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
    Dialog(
        onDismissRequest = { onNoClicked() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier.width(300.dp).padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.dialogue_alert),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(R.string.detail_alert),
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = { onNoClicked() },
                        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.primary)
                    ) {
                        Text(stringResource(R.string.no), color = Color.Black)
                    }
                    TextButton(
                        onClick = { onYesClicked() },
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.White),
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(stringResource(R.string.yes), color = Color.Black)
                    }
                }
            }
        }
    }
}