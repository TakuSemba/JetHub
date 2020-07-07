package com.takusemba.jethub.base.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SystemViewModel @ViewModelInject constructor() : ViewModel() {

  private val mutableIsNightMode: MutableLiveData<Boolean> = MutableLiveData(false)
  val isNightMode: LiveData<Boolean> get() = mutableIsNightMode

  fun isNightMode(): Boolean {
    return requireNotNull(isNightMode.value)
  }

  fun setNightMode(isEnabled: Boolean) {
    if (mutableIsNightMode.value != isEnabled) {
      mutableIsNightMode.value = isEnabled
    }
  }
}
