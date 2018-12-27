package com.takusemba.jethub.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserViewModel @Inject constructor(
  private val userRepository: UserRepository
) : ViewModel(), CoroutineScope {

  override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main

  private val pinedRepositories: MutableLiveData<List<Long>> = MutableLiveData()

  fun pin(id: Long) {
    launch {
      Log.d("UserViewModel", "pin $id")
      userRepository.pin(id)
      val repositories = pinedRepositories.value?.toMutableList() ?: mutableListOf()
      repositories.add(id)
      pinedRepositories.value = repositories
    }
  }

  fun unpin(id: Long) {
    launch {
      Log.d("UserViewModel", "unpin $id")
      userRepository.unpin(id)
      val repositories = pinedRepositories.value?.toMutableList() ?: mutableListOf()
      repositories.remove(id)
      pinedRepositories.value = repositories
    }
  }

  fun pinedRepositories(): LiveData<List<Long>> {
    return pinedRepositories
  }

  fun currentPinnedRepositories(): List<Long> {
    return pinedRepositories.value!!
  }

  override fun onCleared() {
    super.onCleared()
    coroutineContext.cancel()
    Log.d("UserViewModel", "onCleared")
  }
}