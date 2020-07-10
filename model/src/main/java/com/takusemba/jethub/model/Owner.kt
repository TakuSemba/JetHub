package com.takusemba.jethub.model

import org.jetbrains.annotations.TestOnly

data class Owner(
  val id: Int,
  val login: String,
  val avatarUrl: String,
  val url: String
) {

  companion object {

    val EMPTY = Owner(
      id = -1,
      login = "",
      avatarUrl = "",
      url = ""
    )

    @TestOnly
    fun createOwner(
      id: Int = -1,
      login: String = "",
      avatarUrl: String = "",
      url: String = ""
    ) = Owner(
      id = id,
      login = login,
      avatarUrl = avatarUrl,
      url = url
    )
  }
}
