package com.takusemba.jethub.feed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.base.model.ColoredLanguage
import com.takusemba.jethub.model.Repo
import com.takusemba.jethub.repository.SearchRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * [ViewModel] to store and manage Feed data.
 */
class FeedViewModel @ViewModelInject constructor(
  private val searchRepository: SearchRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val mutableHotReposMap = MutableLiveData<Map<String, List<Repo>>>()

  fun hotRepos(language: String): LiveData<List<Repo>> {
    return mutableHotReposMap.map { map -> map[language] ?: emptyList() }
  }

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
