package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.di.screen.DeveloperDetailModule.Qualifiers
import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.DeveloperDetailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class DeveloperDetailViewModel @Inject constructor(
  @Named(Qualifiers.DEVELOPER_NAME) private val developerName: String,
  private val developerDetailRepository: DeveloperDetailRepository
) : ViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  private val developerResult = MutableLiveData<Developer>()
  private val developerReposResult = MutableLiveData<List<Repository>>()

  val developer: LiveData<Developer> = developerResult
  val developerRepos: LiveData<List<Repository>> = developerReposResult

  init {
    launch {
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

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
  }
}