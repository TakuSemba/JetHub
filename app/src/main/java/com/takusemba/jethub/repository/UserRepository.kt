package com.takusemba.jethub.repository

import android.util.Log
import javax.inject.Inject

class UserRepository @Inject constructor() {

  suspend fun pin(id: Long) {
    Log.d("UserRepository", "pin $id")
    // do nothing
  }

  suspend fun unpin(id: Long) {
    Log.d("UserRepository", "unpin $id")
    // do nothing
  }
}