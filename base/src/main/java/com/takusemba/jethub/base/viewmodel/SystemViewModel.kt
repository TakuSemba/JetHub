package com.takusemba.jethub.base.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SystemViewModel @ViewModelInject constructor() : ViewModel() {

  private val _isNightMode: MutableLiveData<Boolean> = MutableLiveData(false)
  val isNightMode: LiveData<Boolean> get() = _isNightMode

  fun isNightMode(): Boolean {
    return requireNotNull(isNightMode.value)
  }

  fun setNightMode(isEnabled: Boolean) {
    if (_isNightMode.value != isEnabled) {
      _isNightMode.value = isEnabled
    }
  }
}