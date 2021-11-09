package com.takusemba.jethub.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val searchRepository: SearchRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val _uiState = MutableStateFlow(SearchUiState.EMPTY)
  val uiState: StateFlow<SearchUiState> = _uiState

  private var searchJob: Job? = null

  init {
    viewModelScope.launch {
      runCatching {
        _uiState.value = _uiState.value.copy(isLoading = true)
        searchRepository.searchRepos("")
      }.onSuccess { repos ->
        _uiState.value = _uiState.value.copy(repos = repos, isLoading = false)
      }.onFailure { error ->
        _uiState.value = _uiState.value.copy(isLoading = false)
        errorHandler.handleError(error)
      }
    }
  }

  fun search(query: String) {
    _uiState.value = _uiState.value.copy(query = query)
    val currentJob = searchJob
    if (currentJob != null && currentJob.isActive) {
      currentJob.cancel()
    }
    searchJob = viewModelScope.launch {
      runCatching {
        searchRepository.searchRepos(query)
      }.onSuccess { repos ->
        _uiState.value = _uiState.value.copy(repos = repos)
      }.onFailure { error ->
        if (error !is CancellationException) {
          errorHandler.handleError(error)
        }
      }
    }
  }
}
