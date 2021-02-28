package com.takusemba.jethub.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SystemViewModel : ViewModel() {

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
