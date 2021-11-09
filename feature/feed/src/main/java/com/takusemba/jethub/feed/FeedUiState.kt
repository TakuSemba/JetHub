package com.takusemba.jethub.feed

import com.takusemba.jethub.model.Repo

data class FeedUiState(
  val hotRepos: Map<String, List<Repo>> = emptyMap(),
  val isLoading: Boolean = false,
) {

  companion object {

    val EMPTY = FeedUiState()
  }
}