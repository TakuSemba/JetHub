package com.takusemba.jethub.developer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.repository.DeveloperRepository
import kotlinx.coroutines.launch

class DeveloperViewModel @ViewModelInject constructor(
  private val name: String,
  private val developerRepository: DeveloperRepository,
  private val errorHandler: ErrorHandler
) : ViewModel() {

  private val mutableDeveloper: MutableLiveData<Developer> = MutableLiveData()
  val developer: LiveData<Developer> get() = mutableDeveloper

  init {
    viewModelScope.launch {
      runCatching {
        developerRepository.getDeveloper(name)
      }.onSuccess { repo ->
        mutableDeveloper.value = repo
      }.onFailure { error ->
        errorHandler.handleError(error)
      }
    }
  }
}