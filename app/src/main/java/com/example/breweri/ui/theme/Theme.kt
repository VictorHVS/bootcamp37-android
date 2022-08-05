package com.example.breweri.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val LightColorPalette = lightColors(
    primary = Yellow,
    primaryVariant = Yellow,
    secondary = White,
    background = Gray,
    error = Red,
    onSurface = Black,
    onBackground = Black
)

@Composable
fun BeesSchoolTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = LightColorPalette,
        typography = RobotoTypography,
        shapes = Shapes,
        content = content
    )
}