package com.takusemba.jethub.model

enum class Language(name: String) {

  KOTLIN("Kotlin"),

  JAVA("Java"),

  OTHER("");

  companion object {

    fun of(name: String?) = when (name) {
      "Kotlin" -> KOTLIN
      "Java" -> JAVA
      else -> OTHER
    }
  }
}