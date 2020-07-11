package com.takusemba.jethub.base.model

sealed class Direction {

  class Repo(val owner: String, val repo: String) : Direction()

  class Developer(val name: String) : Direction()

  object Pop : Direction()
}
