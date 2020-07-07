package com.takusemba.jethub.base.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.RepoRepository
import kotlinx.coroutines.launch

/**
 * [ViewModel] to store and manage user-related data.
 * This should be Activity-Scope, because the data is used across screens.
 */
class UserViewModel @ViewModelInject constructor(
  private val repoRepository: RepoRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val mutablePinedRepositories: MutableLiveData<List<Repository>> = MutableLiveData()
  val pinedRepositories: LiveData<List<Repository>> = mutablePinedRepositories

  init {
    viewModelScope.launch {
      runCatching {
        repoRepository.findAllPins()
      }.onSuccess { repos ->
        mutablePinedRepositories.value = repos
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

  fun pin(repository: Repository) {
    viewModelScope.launch {
      runCatching {
        repoRepository.pin(repository)
        val repositories = mutablePinedRepositories.value?.toMutableList() ?: mutableListOf()
        repositories.apply { add(repository) }
      }.onSuccess { repos ->
        mutablePinedRepositories.value = repos
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

  fun unpin(repository: Repository) {
    viewModelScope.launch {
      runCatching {
        repoRepository.unpin(repository)
        val repositories = mutablePinedRepositories.value?.toMutableList() ?: mutableListOf()
        repositories.apply { remove(repository) }
      }.onSuccess { repos ->
        mutablePinedRepositories.value = repos
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

  fun isPinned(repository: Repository): Boolean {
    return pinedRepositories.value?.contains(repository) ?: false
  }
}
