package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserViewModel @Inject constructor(
  private val userRepository: UserRepository
) : ViewModel(), CoroutineScope {

  // TODO bookmark機能をつける

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  private val pinedRepositoriesResult: MutableLiveData<List<Repository>> = MutableLiveData()

  val pinedRepositories: LiveData<List<Repository>> = pinedRepositoriesResult

  fun pin(repository: Repository) {
    launch {
      userRepository.pin(repository)
      val repositories = pinedRepositoriesResult.value?.toMutableList() ?: mutableListOf()
      repositories.add(repository)
      pinedRepositoriesResult.value = repositories
    }
  }

  fun unpin(repository: Repository) {
    launch {
      userRepository.unpin(repository)
      val repositories = pinedRepositoriesResult.value?.toMutableList() ?: mutableListOf()
      repositories.remove(repository)
      pinedRepositoriesResult.value = repositories
    }
  }

  fun isPinned(repository: Repository): Boolean {
    return pinedRepositories.value?.contains(repository) ?: false
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
  }
}