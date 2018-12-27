package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.extension.map
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.User
import com.takusemba.jethub.repository.FeedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FeedViewModel @Inject constructor(
  private val feedRepository: FeedRepository
) : ViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  private val hotRepositoriesResult = MutableLiveData<Result<List<Repository>>>()
  private val hotUsersResult = MutableLiveData<Result<List<User>>>()

  val hotRepositories: LiveData<List<Repository>>
  val hotUsers: LiveData<List<User>>

  init {
    hotRepositories = hotRepositoriesResult.map { result ->
      result.getOrDefault(emptyList())
    }

    hotUsers = hotUsersResult.map { result ->
      result.getOrDefault(emptyList())
    }

    launch {
      val repositories = runCatching { feedRepository.getHotRepositories() }
      hotRepositoriesResult.postValue(repositories)

      val users = runCatching { feedRepository.getHotUsers() }
      hotUsersResult.postValue(users)
    }
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
  }
}