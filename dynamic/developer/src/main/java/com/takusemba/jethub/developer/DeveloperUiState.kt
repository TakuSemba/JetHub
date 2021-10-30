package com.takusemba.jethub.developer

import com.takusemba.jethub.model.Developer

data class DeveloperUiState(
  val developer: Developer = Developer.EMPTY,
  val isLoading: Boolean = false
) {

  companion object {

    val EMPTY = DeveloperUiState()
  }
}