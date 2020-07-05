package com.takusemba.jethub.repo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.RepoRepository
import kotlinx.coroutines.launch

class RepoViewModel @ViewModelInject constructor(
  private val owner: String,
  private val repo: String,
  private val repoRepository: RepoRepository
) : ViewModel() {

  private val mutableRepository: MutableLiveData<Repository> = MutableLiveData()
  val repository: LiveData<Repository> get() = mutableRepository

  init {
    viewModelScope.launch {
      runCatching {
        repoRepository.getRepo(owner, repo)
      }.onSuccess { repo ->
        mutableRepository.value = repo
      }
    }
  }
}