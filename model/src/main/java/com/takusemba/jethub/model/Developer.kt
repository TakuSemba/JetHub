package com.takusemba.jethub.model

import org.jetbrains.annotations.TestOnly

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

    @TestOnly
    fun createDeveloper(
      id: Int = -1,
      login: String = "",
      avatarUrl: String = "",
      name: String = "",
      company: String = "",
      blog: String = "",
      location: String = "",
      email: String = "",
      bio: String = "",
      publicRepositoriesCount: Int = 0,
      publicGistsCount: Int = 0,
      followersCount: Int = 0,
      followingCount: Int = 0
    ) = Developer(
      id = id,
      login = login,
      avatarUrl = avatarUrl,
      name = name,
      company = company,
      blog = blog,
      location = location,
      email = email,
      bio = bio,
      publicRepositoriesCount = publicRepositoriesCount,
      publicGistsCount = publicGistsCount,
      followersCount = followersCount,
      followingCount = followingCount
    )
  }
}
