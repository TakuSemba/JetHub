package com.takusemba.jethub.model

data class Developer(
  val id: Int,
  val login: String,
  val avatarUrl: String,
  val name: String,
  val company: String,
  val blog: String,
  val location: String,
  val email: String,
  val bio: String,
  val publicRepositoriesCount: Int,
  val publicGistsCount: Int,
  val followersCount: Int,
  val followingCount: Int
) {

  companion object {

    val EMPTY = Developer(
      id = -1,
      login = "",
      avatarUrl = "",
      name = "",
      company = "",
      blog = "",
      location = "",
      email = "",
      bio = "",
      publicRepositoriesCount = 0,
      publicGistsCount = 0,
      followersCount = 0,
      followingCount = 0
    )
  }
}