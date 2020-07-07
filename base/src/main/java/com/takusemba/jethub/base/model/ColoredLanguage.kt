package com.takusemba.jethub.base.model

import androidx.annotation.DrawableRes
import com.takusemba.jethub.base.R

enum class ColoredLanguage(val title: String, @DrawableRes val icon: Int) {

  KOTLIN("Kotlin", R.drawable.ic_circle_kotlin),

  JAVA("Java", R.drawable.ic_circle_java),

  JAVA_SCRIPT("JavaScript", R.drawable.ic_circle_java_script),

  PYTHON("Python", R.drawable.ic_circle_python),

  CSS("CSS", R.drawable.ic_circle_css),

  PHP("PHP", R.drawable.ic_circle_php),

  RUBY("Ruby", R.drawable.ic_circle_ruby),

  C_PLUS_PLUS("C++", R.drawable.ic_circle_c_plus_plus),

  C("C", R.drawable.ic_circle_c),

  GO("Go", R.drawable.ic_circle_go),

  SWIFT("Swift", R.drawable.ic_circle_swift),

  OTHER("Other", R.drawable.ic_circle_other);

  companion object {

    val POPULAR_LANGUAGES = listOf(
      KOTLIN,
      JAVA,
      SWIFT,
      PYTHON,
      RUBY,
      GO
    )

    fun of(name: String?) = when (name) {
      KOTLIN.title -> KOTLIN
      JAVA.title -> JAVA
      JAVA_SCRIPT.title -> JAVA_SCRIPT
      PYTHON.title -> PYTHON
      CSS.title -> CSS
      PHP.title -> PHP
      RUBY.title -> RUBY
      C_PLUS_PLUS.title -> C_PLUS_PLUS
      C.title -> C
      GO.title -> GO
      SWIFT.title -> SWIFT
      OTHER.title -> OTHER
      else -> OTHER
    }
  }
}
