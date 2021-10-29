package com.takusemba.jethub.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.model.Repo
import com.takusemba.jethub.repository.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
  private val owner: String,
  private val repo: String,
  private val repoRepository: RepoRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val mutableRepo: MutableStateFlow<Repo> = MutableStateFlow(Repo.EMPTY)
  val repository: StateFlow<Repo> get() = mutableRepo

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
