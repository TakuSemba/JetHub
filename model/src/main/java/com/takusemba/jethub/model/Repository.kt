package com.takusemba.jethub.model

import com.takusemba.jethub.model.Owner.Companion.createOwner
import org.jetbrains.annotations.TestOnly
import java.time.LocalDateTime

data class Repository(
  val id: Int,
  val owner: Owner,
  val name: String,
  val description: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
  val starsCount: Int,
  val watchersCount: Int,
  val forksCount: Int,
  val language: String
) {

  companion object {

    @TestOnly
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
  }
}
