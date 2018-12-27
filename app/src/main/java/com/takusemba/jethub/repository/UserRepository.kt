package com.takusemba.jethub.repository

import android.util.Log
import javax.inject.Inject

class UserRepository @Inject constructor() {

  suspend fun pin(id: Long) {
//    Log.d("UserRepository ${System.identityHashCode(this)}", "pin $id")
    // do nothing
  }

  suspend fun unpin(id: Long) {
//    Log.d("UserRepository ${System.identityHashCode(this)}", "unpin $id")
    // do nothing
  }
}