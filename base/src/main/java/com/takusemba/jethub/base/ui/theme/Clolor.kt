package com.takusemba.jethub.base.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val Primary = Color(0xff6da1f0)
private val PrimaryDark = Color(0xff4688f1)
private val Accent = Color(0xff4688f1)

val LightThemeColors = lightColors(
  primary = Primary,
  primaryVariant = PrimaryDark,
  secondary = Accent
)

val DarkThemeColors = darkColors(
  primary = Primary,
  primaryVariant = PrimaryDark,
  secondary = Accent
)
