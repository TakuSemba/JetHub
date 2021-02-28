package com.takusemba.jethub.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.model.Repo
import com.takusemba.jethub.repository.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

/**
 * [ViewModel] to store and manage user-related data.
 * This should be Activity-Scope, because the data is used across screens.
 */
@HiltViewModel
class UserViewModel constructor(
  private val repoRepository: RepoRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val mutablePinedRepositories: MutableLiveData<List<Repo>> = MutableLiveData()
  val pinedRepositories: LiveData<List<Repo>> = mutablePinedRepositories

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

  fun pin(repo: Repo) {
    viewModelScope.launch {
      runCatching {
        repoRepository.pin(repo)
        val repositories = mutablePinedRepositories.value?.toMutableList() ?: mutableListOf()
        repositories.apply { add(repo) }
      }.onSuccess { repos ->
        mutablePinedRepositories.value = repos
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

  fun unpin(repo: Repo) {
    viewModelScope.launch {
      runCatching {
        repoRepository.unpin(repo)
        val repositories = mutablePinedRepositories.value?.toMutableList() ?: mutableListOf()
        repositories.apply { remove(repo) }
      }.onSuccess { repos ->
        mutablePinedRepositories.value = repos
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }

  fun isPinned(repo: Repo): Boolean {
    return pinedRepositories.value?.contains(repo) ?: false
  }
}
