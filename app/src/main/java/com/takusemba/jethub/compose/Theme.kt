package com.takusemba.jethub.compose

import androidx.compose.Composable
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette

private val LightThemeColors = lightColorPalette(
  primary = Primary,
  primaryVariant = PrimaryDark,
  secondary = Accent
)

private val DarkThemeColors = darkColorPalette(
  primary = Primary,
  primaryVariant = PrimaryDark,
  secondary = Accent
)

@Composable
fun JethubTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable() () -> Unit
) {
  MaterialTheme(
    colors = if (darkTheme) DarkThemeColors else LightThemeColors,
    typography = ThemeTypography,
    content = content
  )
}
