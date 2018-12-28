package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.extension.map
import com.takusemba.jethub.model.User
import com.takusemba.jethub.repository.SearchUsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchUsersViewModel @Inject constructor(
  private val searchUsersRepository: SearchUsersRepository
) : ViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  private val searchedUsersResult = MutableLiveData<Result<List<User>>>()

  val searchedUsers: LiveData<List<User>>

  init {
    searchedUsers = searchedUsersResult.map { result ->
      result.getOrDefault(emptyList())
    }

    launch {
      val repositories = runCatching { searchUsersRepository.searchUsers("") }
      searchedUsersResult.value = repositories
    }
  }

  fun search(query: String) {
    launch {
      val users = runCatching { searchUsersRepository.searchUsers(query) }
      searchedUsersResult.value = users
    }
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
  }
}