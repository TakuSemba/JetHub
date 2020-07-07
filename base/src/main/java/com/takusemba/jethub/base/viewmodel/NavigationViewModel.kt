package com.takusemba.jethub.base.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.base.model.Direction
import com.takusemba.jethub.base.model.Event

/**
 * [ViewModel] to manage directions, because feature module can not reference to a direction.
 */
class NavigationViewModel @ViewModelInject constructor() : ViewModel() {

  private val mutableDirection: MutableLiveData<Event<Direction>> = MutableLiveData()
  val direction: LiveData<Event<Direction>> = mutableDirection

  fun openRepo(owner: String, repo: String) {
    mutableDirection.value = Event(Direction.Repo(owner, repo))
  }

  fun openDeveloper(name: String) {
    mutableDirection.value = Event(Direction.Developer(name))
  }
}
