package com.takusemba.jethub.feed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.RepoRepository
import kotlinx.coroutines.launch

/**
 * [ViewModel] to store and manage Feed data.
 */
class FeedViewModel @ViewModelInject constructor(
  private val repoRepository: RepoRepository
) : ViewModel() {

  private val mutableHotReposMap = MutableLiveData<Map<Language, List<Repository>>>()

  fun hotRepos(language: Language): LiveData<List<Repository>> {
    return mutableHotReposMap.map { map -> map[language] ?: emptyList() }
  }

  init {
    viewModelScope.launch {
      runCatching {
        mutableMapOf<Language, List<Repository>>().also { map ->
          Language.POPULAR_LANGUAGES.forEach { language ->
            map[language] = repoRepository.getHotRepos(language.title)
          }
        }
      }.onSuccess { map ->
        mutableHotReposMap.value = map
      }
    }
  }
}