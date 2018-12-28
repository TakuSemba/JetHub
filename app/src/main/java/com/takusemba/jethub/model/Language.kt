package com.takusemba.jethub.model

import androidx.annotation.DrawableRes
import com.takusemba.jethub.R

enum class Language(val title: String, @DrawableRes val icon: Int) {

  KOTLIN("Kotlin", R.drawable.ic_circle_orange),

  JAVA("Java", R.drawable.ic_circle_brown),

  OTHER("", R.drawable.ic_circle_gray);

  companion object {

    fun of(name: String?) = when (name) {
      "Kotlin" -> KOTLIN
      "Java" -> JAVA
      else -> OTHER
    }
  }
}