package com.example.frux.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = TextColor,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = CardBackground,
    onPrimary = TextColorWhiteBackground,
    onSecondary = TextColorWhiteBackground,
    onBackground = TextColorWhiteBackground
)
private val LightColorPalette = lightColors(
    primary = TextColorReverse,
    primaryVariant = Purple700Reverse,
    secondary = Teal200Reverse,
    background = CardBackgroundReverse,
    onPrimary = TextColorWhiteBackgroundReverse,
    onSecondary = TextColorWhiteBackgroundReverse,
    onBackground = TextColorWhiteBackgroundReverse
)

@Composable
fun FruxTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}