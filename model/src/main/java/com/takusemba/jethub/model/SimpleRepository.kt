package com.takusemba.jethub.model

import org.jetbrains.annotations.TestOnly

data class SimpleRepository(
  val id: Int,
  val name: String,
  val owner: String
) {

  companion object {

    @TestOnly
    fun createSimpleRepository(
      id: Int = -1,
      name: String = "",
      owner: String = ""
    ) = SimpleRepository(
      id = id,
      name = name,
      owner = owner
    )
  }
}