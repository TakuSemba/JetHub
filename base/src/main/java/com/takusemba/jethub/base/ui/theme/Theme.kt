package com.takusemba.jethub.base.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun JethubTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
    typography = Typography,
  ) {
    // TODO (M3): MaterialTheme doesn't provide LocalIndication, remove when it does
    val rippleIndication = rememberRipple()
    CompositionLocalProvider(
      LocalIndication provides rippleIndication,
      content = content
    )
  }
}
