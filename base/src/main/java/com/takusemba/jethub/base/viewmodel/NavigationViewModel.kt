package com.takusemba.jethub.base.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takusemba.jethub.base.model.Direction

/**
 * [ViewModel] to manage directions, because feature module can not reference to a direction.
 */
class NavigationViewModel @ViewModelInject constructor() : ViewModel() {

  private val mutableDirection: MutableLiveData<Direction> = MutableLiveData()
  val direction: LiveData<Direction> = mutableDirection

  fun onDirectionChanged(direction: Direction) {
    mutableDirection.value = direction
  }
}