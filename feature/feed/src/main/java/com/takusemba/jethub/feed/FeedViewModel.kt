package com.takusemba.jethub.feed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.model.ColoredLanguage
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.RepoRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

/**
 * [ViewModel] to store and manage Feed data.
 */
class FeedViewModel @ViewModelInject constructor(
  private val repoRepository: RepoRepository
) : ViewModel() {

  private val mutableHotReposMap = MutableLiveData<Map<String, List<Repository>>>()

  fun hotRepos(language: String): LiveData<List<Repository>> {
    return mutableHotReposMap.map { map -> map[language] ?: emptyList() }
  }

  init {
    viewModelScope.launch {
      runCatching {
        mutableMapOf<String, List<Repository>>().also { map ->
          ColoredLanguage.POPULAR_LANGUAGES.map { language ->
            async { map[language.title] = repoRepository.getHotRepos(language.title) }
          }.awaitAll()
        }
      }.onSuccess { map ->
        mutableHotReposMap.value = map
      }
    }
  }
}