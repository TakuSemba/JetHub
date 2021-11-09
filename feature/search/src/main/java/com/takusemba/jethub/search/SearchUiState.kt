package com.takusemba.jethub.search

import com.takusemba.jethub.model.Repo

data class SearchUiState(
  val query: String = "",
  val repos: List<Repo> = emptyList(),
  val isLoading: Boolean = false,
) {

  companion object {

    val EMPTY = SearchUiState()
  }
}