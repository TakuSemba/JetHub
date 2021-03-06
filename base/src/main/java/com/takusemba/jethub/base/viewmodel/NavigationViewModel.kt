package com.takusemba.jethub.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.base.model.Direction
import com.takusemba.jethub.base.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * [ViewModel] to manage directions, because feature module can not reference to a direction.
 */
@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

  private val mutableDirection: MutableLiveData<Event<Direction>> = MutableLiveData()
  val direction: LiveData<Event<Direction>> = mutableDirection

  fun openRepo(owner: String, repo: String) {
    mutableDirection.value = Event(Direction.Repo(owner, repo))
  }

  fun openDeveloper(name: String) {
    mutableDirection.value = Event(Direction.Developer(name))
  }

  fun popBackStack() {
    mutableDirection.value = Event(Direction.Pop)
  }
}
