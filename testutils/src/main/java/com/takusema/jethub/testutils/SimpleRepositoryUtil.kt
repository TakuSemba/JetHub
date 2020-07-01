package com.takusema.jethub.testutils

import com.takusemba.jethub.model.SimpleRepository

fun createSimpleRepository(
  id: Int = -1,
  name: String = "",
  owner: String = ""
) = SimpleRepository(
  id = id,
  name = name,
  owner = owner
)