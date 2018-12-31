package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.model.SimpleDeveloper
import com.takusemba.jethub.repository.SearchDevelopersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchDevelopersViewModel @Inject constructor(
  private val searchDevelopersRepository: SearchDevelopersRepository
) : ViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  private val searchedDevelopersResult = MutableLiveData<List<SimpleDeveloper>>()

  val searchedDevelopers: LiveData<List<SimpleDeveloper>> = searchedDevelopersResult

  init {
    launch {
      runCatching {
        searchDevelopersRepository.searchDevelopers("")
      }.onSuccess { developers ->
        searchedDevelopersResult.value = developers
      }
    }
  }

  fun search(query: String) {
    launch {
      runCatching {
        searchDevelopersRepository.searchDevelopers(query)
      }.onSuccess { developers ->
        searchedDevelopersResult.value = developers
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
  }
}