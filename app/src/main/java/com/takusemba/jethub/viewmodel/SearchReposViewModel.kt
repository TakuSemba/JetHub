package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.extension.map
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.SearchReposRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchReposViewModel @Inject constructor(
  private val searchReposRepository: SearchReposRepository
) : ViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  private val searchedReposResult = MutableLiveData<Result<List<Repository>>>()

  val searchedRepos: LiveData<List<Repository>>

  init {
    searchedRepos = searchedReposResult.map { result ->
      result.getOrDefault(emptyList())
    }

    launch {
      val repositories = runCatching { searchReposRepository.searchRepos("") }
      searchedReposResult.value = repositories
    }
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
  }
}