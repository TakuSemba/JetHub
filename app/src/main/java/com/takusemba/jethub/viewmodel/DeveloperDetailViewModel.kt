package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.di.screen.DeveloperDetailModule.Qualifiers
import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.DeveloperDetailRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

/**
 * [ViewModel] to store and manage DeveloperDetail data.
 */
class DeveloperDetailViewModel @Inject constructor(
  @Named(Qualifiers.DEVELOPER_NAME) private val developerName: String,
  private val developerDetailRepository: DeveloperDetailRepository
) : ViewModel() {

  private val developerResult = MutableLiveData<Developer>()
  private val developerReposResult = MutableLiveData<List<Repository>>()

  val developer: LiveData<Developer> = developerResult
  val developerRepos: LiveData<List<Repository>> = developerReposResult

  init {
    viewModelScope.launch {
      runCatching {
        developerDetailRepository.getDeveloper(developerName)
      }.onSuccess { developer ->
        developerResult.value = developer
      }

      runCatching {
        developerDetailRepository.getRepos(developerName)
      }.onSuccess { developerRepos ->
        developerReposResult.value = developerRepos
      }
    }
  }
}