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

  private val mutableHotReposMap = MutableStateFlow<Map<String, List<Repo>>>(emptyMap())
  val hotReposMap: StateFlow<Map<String, List<Repo>>> = mutableHotReposMap

  init {
    viewModelScope.launch {
      runCatching {
        mutableMapOf<String, List<Repo>>().also { map ->
          // https://medium.com/@elizarov/structured-concurrency-722d765aa952
          coroutineScope {
            ColoredLanguage.POPULAR_LANGUAGES.map { language ->
              async { map[language.title] = searchRepository.searchHotRepos(language.title) }
            }.awaitAll()
          }
        }
      }.onSuccess { map ->
        mutableHotReposMap.value = map
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }
}
