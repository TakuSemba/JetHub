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

  private val searchedUsersResult = MutableLiveData<List<SimpleDeveloper>>()

  val searchedUsers: LiveData<List<SimpleDeveloper>> = searchedUsersResult

  init {
    launch {
      runCatching {
        searchDevelopersRepository.searchUsers("")
      }.onSuccess { repos ->
        searchedUsersResult.value = repos
      }
    }
  }

  fun search(query: String) {
    launch {
      runCatching {
        searchDevelopersRepository.searchUsers(query)
      }.onSuccess { developers ->
        searchedUsersResult.value = developers
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
  }
}