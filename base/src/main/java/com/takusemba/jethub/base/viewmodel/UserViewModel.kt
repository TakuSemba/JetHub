package com.takusemba.jethub.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.model.Repo
import com.takusemba.jethub.repository.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] to store and manage user-related data.
 * This should be Activity-Scope, because the data is used across screens.
 */
@HiltViewModel
class UserViewModel @Inject constructor(
  private val repoRepository: RepoRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val _uiState: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState.EMPTY)
  val uiState: StateFlow<UserUiState> = _uiState

  init {
    viewModelScope.launch {
      runCatching {
        repoRepository.findAllPins()
      }.onSuccess { repos ->
        _uiState.value = _uiState.value.copy(pinnedRepos = repos)
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

  fun pin(repo: Repo) {
    viewModelScope.launch {
      runCatching {
        repoRepository.pin(repo)
        val repositories = _uiState.value.pinnedRepos.toMutableList()
        repositories.apply { add(repo) }
      }.onSuccess { repos ->
        _uiState.value = _uiState.value.copy(pinnedRepos = repos)
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

  fun unpin(repo: Repo) {
    viewModelScope.launch {
      runCatching {
        repoRepository.unpin(repo)
        val repositories = _uiState.value.pinnedRepos.toMutableList()
        repositories.apply { remove(repo) }
      }.onSuccess { repos ->
        _uiState.value = _uiState.value.copy(pinnedRepos = repos)
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

  fun isPinned(repo: Repo): Boolean {
    return _uiState.value.pinnedRepos.contains(repo)
  }
}
