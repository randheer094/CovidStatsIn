package me.randheer.covidstatsin.android.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    onPrimary = Color.White,
    secondary = Black,
    secondaryVariant = Accent,
    onSecondary = Color.White,
    surface = Color.White
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = AppTypes,
        shapes = AppShapes,
        content = content
    )
}