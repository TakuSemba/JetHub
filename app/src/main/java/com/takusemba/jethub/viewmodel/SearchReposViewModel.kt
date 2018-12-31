package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.SearchReposRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * [ViewModel] to store and manage searched repos data.
 */
class SearchReposViewModel @Inject constructor(
  private val searchReposRepository: SearchReposRepository
) : ViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  private val mutableSearchedRepos = MutableLiveData<List<Repository>>()
  val searchedRepos: LiveData<List<Repository>> = mutableSearchedRepos

  init {
    launch {
      runCatching {
        searchReposRepository.searchRepos("")
      }.onSuccess { repos ->
        mutableSearchedRepos.value = repos
      }
    }
  }

  fun search(query: String) {
    launch {
      runCatching {
        searchReposRepository.searchRepos(query)
      }.onSuccess { repos ->
        mutableSearchedRepos.value = repos
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
  }
}