package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.SearchReposRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] to store and manage searched repos data.
 */
class SearchReposViewModel @Inject constructor(
  private val searchReposRepository: SearchReposRepository
) : ViewModel() {

  private val mutableSearchedRepos = MutableLiveData<List<Repository>>()
  val searchedRepos: LiveData<List<Repository>> = mutableSearchedRepos

  init {
    viewModelScope.launch {
      runCatching {
        searchReposRepository.searchRepos("")
      }.onSuccess { repos ->
        mutableSearchedRepos.value = repos
      }
    }
  }

  fun search(query: String) {
    viewModelScope.launch {
      runCatching {
        searchReposRepository.searchRepos(query)
      }.onSuccess { repos ->
        mutableSearchedRepos.value = repos
      }
    }
  }
}