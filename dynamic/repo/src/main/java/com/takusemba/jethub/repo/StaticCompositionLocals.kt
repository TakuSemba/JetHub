package com.takusemba.jethub.repo

import androidx.compose.runtime.staticCompositionLocalOf
import io.noties.markwon.Markwon

val LocalMarkwon = staticCompositionLocalOf<Markwon> {
  error("Markwon not found")
}
