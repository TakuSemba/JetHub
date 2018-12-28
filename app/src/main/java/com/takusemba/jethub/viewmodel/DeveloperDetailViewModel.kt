package com.takusemba.jethub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.extension.map
import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.DeveloperDetailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DeveloperDetailViewModel @Inject constructor(
  private val developerName: String,
  private val developerDetailRepository: DeveloperDetailRepository
) : ViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  private val developerResult = MutableLiveData<Result<Developer>>()
  private val developerReposResult = MutableLiveData<Result<List<Repository>>>()

  val developer: LiveData<Developer>
  val developerRepos: LiveData<List<Repository>>

  init {
    developer = developerResult.map { result ->
      result.getOrDefault(Developer.EMPTY)
    }

    developerRepos = developerReposResult.map { result ->
      result.getOrDefault(emptyList())
    }

    launch {
      val developer = runCatching { developerDetailRepository.getDeveloper(developerName) }
      developerResult.value = developer

      val developerRepos = runCatching { developerDetailRepository.getRepos(developerName) }
      developerReposResult.value = developerRepos
    }
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
  }
}