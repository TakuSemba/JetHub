package com.takusemba.jethub.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.RepoRepository
import kotlinx.coroutines.launch

/**
 * [ViewModel] to store and manage Feed Channel data.
 */
class FeedChannelViewModel(
  private val languageName: String,
  private val repoRepository: RepoRepository
) : ViewModel() {

  private val mutableHotRepos = MutableLiveData<List<Repository>>()
  val hotRepos = mutableHotRepos

  init {
    viewModelScope.launch {
      runCatching {
        repoRepository.getHotRepos(languageName)
      }.onSuccess { repos ->
        hotRepos.value = repos
      }
    }
  }
}