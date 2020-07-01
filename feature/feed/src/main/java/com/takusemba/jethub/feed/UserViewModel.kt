package com.takusemba.jethub.feed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.UserRepository
import kotlinx.coroutines.launch

/**
 * [ViewModel] to store and manage user-related data.
 * This should be Activity-Scope, because the data is used across screens.
 */
class UserViewModel @ViewModelInject constructor(
  private val userRepository: UserRepository
) : ViewModel() {

  private val mutablePinedRepositories: MutableLiveData<List<Repository>> = MutableLiveData()
  val pinedRepositories: LiveData<List<Repository>> = mutablePinedRepositories

  init {
    viewModelScope.launch {
      runCatching {
        userRepository.findAllPins()
      }.onSuccess { repos -> mutablePinedRepositories.value = repos }
    }
  }

  fun pin(repository: Repository) {
    viewModelScope.launch {
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
    viewModelScope.launch {
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
}