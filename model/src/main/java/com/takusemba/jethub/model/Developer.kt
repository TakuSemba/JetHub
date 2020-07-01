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
)