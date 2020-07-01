package com.takusema.jethub.testutils

import com.takusemba.jethub.model.Owner

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