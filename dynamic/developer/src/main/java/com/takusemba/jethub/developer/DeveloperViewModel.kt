package com.takusemba.jethub.developer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.repository.DeveloperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeveloperViewModel @Inject constructor(
  private val name: String,
  private val developerRepository: DeveloperRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val _uiState = MutableStateFlow(DeveloperUiState.EMPTY)
  val uiState: StateFlow<DeveloperUiState> get() = _uiState

  init {
    viewModelScope.launch {
      runCatching {
        developerRepository.getDeveloper(name)
      }.onSuccess { developer ->
        _uiState.value = _uiState.value.copy(developer = developer)
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }
}
