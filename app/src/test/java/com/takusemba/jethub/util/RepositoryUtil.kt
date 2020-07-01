package com.takusemba.jethub.util

import com.takusemba.jethub.model.Owner
import com.takusemba.jethub.model.Repository
import java.time.LocalDateTime

fun createRepository(
  id: Int = -1,
  owner: Owner = createOwner(),
  name: String = "",
  description: String = "",
  createdAt: LocalDateTime = LocalDateTime.now(),
  updatedAt: LocalDateTime = LocalDateTime.now(),
  starsCount: Int = 0,
  watchersCount: Int = 0,
  forksCount: Int = 0,
  language: String = "Kotlin"
) = Repository(
  id = id,
  owner = owner,
  name = name,
  description = description,
  createdAt = createdAt,
  updatedAt = updatedAt,
  starsCount = starsCount,
  watchersCount = watchersCount,
  forksCount = forksCount,
  language = language
)