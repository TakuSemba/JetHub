package com.takusemba.jethub.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.model.Repo
import com.takusemba.jethub.repository.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class RepoViewModel constructor(
  private val owner: String,
  private val repo: String,
  private val repoRepository: RepoRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val mutableRepo: MutableLiveData<Repo> = MutableLiveData()
  val repository: LiveData<Repo> get() = mutableRepo

  init {
    viewModelScope.launch {
      runCatching {
        repoRepository.getRepo(owner, repo)
      }.onSuccess { repo ->
        mutableRepo.value = repo
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }
}
