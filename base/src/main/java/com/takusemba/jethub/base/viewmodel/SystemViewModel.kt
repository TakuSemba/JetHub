package com.takusemba.jethub.base.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SystemViewModel @Inject constructor() : ViewModel() {

  private val mutableIsNightMode: MutableStateFlow<Boolean> = MutableStateFlow(false)
  val isNightMode: StateFlow<Boolean> get() = mutableIsNightMode

  fun isNightMode(): Boolean {
    return requireNotNull(isNightMode.value)
  }

  fun setNightMode(isEnabled: Boolean) {
    if (mutableIsNightMode.value != isEnabled) {
      mutableIsNightMode.value = isEnabled
    }
  }
}
