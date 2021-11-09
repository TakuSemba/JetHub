package com.takusemba.jethub.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.base.model.ColoredLanguage
import com.takusemba.jethub.model.Repo
import com.takusemba.jethub.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
  private val searchRepository: SearchRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val _uiState = MutableStateFlow(FeedUiState.EMPTY)
  val uiState: StateFlow<FeedUiState> = _uiState

  init {
    viewModelScope.launch {
      runCatching {
        _uiState.value = _uiState.value.copy(isLoading = true)
        mutableMapOf<String, List<Repo>>().also { map ->
          // https://medium.com/@elizarov/structured-concurrency-722d765aa952
          coroutineScope {
            ColoredLanguage.POPULAR_LANGUAGES.map { language ->
              async { map[language.title] = searchRepository.searchHotRepos(language.title) }
            }.awaitAll()
          }
        }
      }.onSuccess { hotRepos ->
        _uiState.value = _uiState.value.copy(hotRepos = hotRepos, isLoading = false)
      }.onFailure { error ->
        _uiState.value = _uiState.value.copy(isLoading = false)
        errorHandler.handleError(error)
      }
    }
  }
}
