package com.takusemba.jethub.repo

import com.takusemba.jethub.model.ReadMe
import com.takusemba.jethub.model.Repo

data class RepoUiState(
  val repo: Repo = Repo.EMPTY,
  val readMe: ReadMe = ReadMe.EMPTY,
  val isLoading: Boolean = false
) {

  companion object {

    val EMPTY = RepoUiState()
  }
}