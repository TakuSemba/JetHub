package com.takusemba.jethub.base.viewmodel

import com.takusemba.jethub.model.Repo

data class UserUiState(
  val pinnedRepos: List<Repo> = emptyList(),
) {

  companion object {

    val EMPTY = UserUiState()
  }
}