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

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  private val mutablePinedRepositories: MutableLiveData<List<Repository>> = MutableLiveData()
  val pinedRepositories: LiveData<List<Repository>> = mutablePinedRepositories

  init {
    launch {
      runCatching {
        userRepository.findAll()
      }.onSuccess { repos -> mutablePinedRepositories.value = repos }
    }
  }

  fun pin(repository: Repository) {
    launch {
      runCatching {
        userRepository.pin(repository)
        val repositories = mutablePinedRepositories.value?.toMutableList() ?: mutableListOf()
        repositories.apply { add(repository) }
      }.onSuccess { repos ->
        mutablePinedRepositories.value = repos
      }
    }
  }

  fun unpin(repository: Repository) {
    launch {
      runCatching {
        userRepository.unpin(repository)
        val repositories = mutablePinedRepositories.value?.toMutableList() ?: mutableListOf()
        repositories.apply { remove(repository) }
      }.onSuccess { repos ->
        mutablePinedRepositories.value = repos
      }
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