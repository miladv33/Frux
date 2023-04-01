package com.example.frux.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun ArcRotationWithLogo() {
    val infiniteTransition = rememberInfiniteTransition()


    val arcColor = MaterialTheme.colors.primary
    val arcAngle1 by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 180F,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val arcAngle2 by infiniteTransition.animateFloat(
        initialValue = 180F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )


    val arc by infiniteTransition.animateFloat(
        initialValue =0F,
        targetValue = 90F,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val alphaAnimate by infiniteTransition.animateFloat(
        initialValue = 0.3F,
        targetValue = 1F,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val pxValue = with(LocalDensity.current) { 36.dp.toPx() }
val inset = with(LocalDensity.current) { 7.dp.toPx() }
    val bitmap = getBitmapFromImage(LocalContext.current, com.example.frux.R.drawable.ic_launcher_background , pxValue).asImageBitmap()

    val colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .padding(12.dp)
                .size(50.dp)
        ) {
            drawArc(
                color = arcColor,
                startAngle = arcAngle1,
                sweepAngle = arc,
                useCenter = false,
                style = Stroke(width = 6f, cap = StrokeCap.Round),
            )

            drawArc(
                color = arcColor,
                startAngle = arcAngle2,
                sweepAngle = arc,
                useCenter = false,
                style = Stroke(width = 6f, cap = StrokeCap.Round),

            )
            inset(horizontal = inset, vertical = inset) {
                drawImage(
                    bitmap,
                    colorFilter =colorFilter,

                )
            }
        }
    }
}
@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }
private fun getBitmapFromImage(context: Context, drawable: Int, pxValue: Float): Bitmap {

    // on below line we are getting drawable
    val db = ContextCompat.getDrawable(context, drawable)

    // in below line we are creating our bitmap and initializing it.
    val bit = Bitmap.createBitmap(
        pxValue.toInt(), pxValue.toInt(), Bitmap.Config.ARGB_8888
    )

    // on below line we are
    // creating a variable for canvas.

    val canvas = android.graphics.Canvas(bit)

    // on below line we are setting bounds for our bitmap.
    db!!.setBounds(0, 0, canvas.width, canvas.height)

    // on below line we are simply
    // calling draw to draw our canvas.
    db!!.draw(canvas)

    // on below line we are
    // returning our bitmap.
    return bit
}