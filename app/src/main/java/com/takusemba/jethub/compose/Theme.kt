package com.takusemba.jethub.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightThemeColors = lightColors(
  primary = Primary,
  primaryVariant = PrimaryDark,
  secondary = Accent
)

private val DarkThemeColors = darkColors(
  primary = Primary,
  primaryVariant = PrimaryDark,
  secondary = Accent
)

@Composable
fun JethubTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colors = if (darkTheme) DarkThemeColors else LightThemeColors,
    typography = ThemeTypography,
    content = content
  )
}
