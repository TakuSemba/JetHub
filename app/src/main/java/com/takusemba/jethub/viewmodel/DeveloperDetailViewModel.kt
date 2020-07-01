package com.takusemba.jethub.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.DeveloperRepository
import kotlinx.coroutines.launch

/**
 * [ViewModel] to store and manage DeveloperDetail data.
 */
class DeveloperDetailViewModel @ViewModelInject constructor(
  private val developerRepository: DeveloperRepository
) : ViewModel() {

  private val developerResult = MutableLiveData<Developer>()
  private val developerReposResult = MutableLiveData<List<Repository>>()

  val developer: LiveData<Developer> = developerResult
  val developerRepos: LiveData<List<Repository>> = developerReposResult

  fun load(developerName: String) = viewModelScope.launch {
    runCatching {
      developerRepository.getDeveloper(developerName)
    }.onSuccess { developer ->
      developerResult.value = developer
    }

    runCatching {
      developerRepository.getDeveloperRepos(developerName)
    }.onSuccess { developerRepos ->
      developerReposResult.value = developerRepos
    }
  }
}