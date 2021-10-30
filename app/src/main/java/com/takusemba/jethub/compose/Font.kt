package com.takusemba.jethub.compose

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.takusemba.jethub.R

private val RobotoMono = FontFamily(
  Font(R.font.roboto_mono_regular, FontWeight.Normal),
  Font(R.font.roboto_mono_medium, FontWeight.Medium),
  Font(R.font.roboto_mono_bold, FontWeight.Bold)
)

val ThemeTypography = Typography(
  defaultFontFamily = RobotoMono
)
