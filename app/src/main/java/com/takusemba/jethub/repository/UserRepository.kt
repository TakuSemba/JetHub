package com.takusemba.jethub.repository

import android.util.Log

class UserRepository {

  suspend fun pin(id: Long) {
    Log.d("UserRepository", "pin repository")
    // do nothing
  }

  suspend fun unpin(id: Long) {
    Log.d("UserRepository", "unpin repository")
    // do nothing
  }
}