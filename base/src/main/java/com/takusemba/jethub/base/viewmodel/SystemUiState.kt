package com.takusemba.jethub.base.viewmodel

data class SystemUiState(
  val isNightMode: Boolean = false,
) {

  companion object {

    val EMPTY = SystemUiState()
  }
}