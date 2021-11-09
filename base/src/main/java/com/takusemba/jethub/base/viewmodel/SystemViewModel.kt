package com.takusemba.jethub.base.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SystemViewModel @Inject constructor() : ViewModel() {

  private val _uiState: MutableStateFlow<SystemUiState> = MutableStateFlow(SystemUiState.EMPTY)
  val uiState: StateFlow<SystemUiState> = _uiState

  fun isNightMode(): Boolean {
    return requireNotNull(uiState.value.isNightMode)
  }

  fun toggleNightMode() {
    _uiState.value = _uiState.value.copy(isNightMode = !_uiState.value.isNightMode)
  }
}
