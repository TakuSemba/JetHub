package com.takusemba.jethub.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.SearchRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * [ViewModel] to store and manage searched repos data.
 */
class SearchViewModel @ViewModelInject constructor(
  private val searchRepository: SearchRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val mutableSearchedRepos = MutableLiveData<List<Repository>>()
  val searchedRepos: LiveData<List<Repository>> = mutableSearchedRepos

  private var searchJob: Job? = null

  init {
    viewModelScope.launch {
      runCatching {
        searchRepository.searchRepos("")
      }.onSuccess { repos ->
        mutableSearchedRepos.value = repos
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

  fun search(query: String) {
    val currentJob = searchJob
    if (currentJob != null && currentJob.isActive) {
      currentJob.cancel()
    }
    searchJob = viewModelScope.launch {
      runCatching {
        delay(500)
        searchRepository.searchRepos(query)
      }.onSuccess { repos ->
        mutableSearchedRepos.value = repos
      }.onFailure { error ->
        if (error !is CancellationException) {
          errorHandler.handleError(error)
        }
      }
    }
  }
}
