package com.takusemba.jethub.base.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

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
