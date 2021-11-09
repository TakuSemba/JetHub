package com.takusemba.jethub.base.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.staticCompositionLocalOf

val LocalActivity = staticCompositionLocalOf<AppCompatActivity> {
  error("Activity not found")
}