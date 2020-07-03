package com.takusemba.jethub.model

import org.jetbrains.annotations.TestOnly

data class SimpleDeveloper(
  val id: Int,
  val login: String,
  val avatarUrl: String
) {

  companion object {

    @TestOnly
    fun createSimpleDeveloper(
      id: Int = -1,
      login: String = "",
      avatarUrl: String = ""
    ) = SimpleDeveloper(
      id = id,
      login = login,
      avatarUrl = avatarUrl
    )
  }
}