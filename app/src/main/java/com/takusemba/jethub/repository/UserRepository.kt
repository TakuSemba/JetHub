package com.takusemba.jethub.repository

import com.takusemba.jethub.model.Repository
import javax.inject.Inject

class UserRepository @Inject constructor() {

  // TODO bookmarkをroomで保存する

  suspend fun pin(repository: Repository) {
//    Log.d("UserRepository ${System.identityHashCode(this)}", "pin $id")
    // do nothing
  }

  suspend fun unpin(repository: Repository) {
//    Log.d("UserRepository ${System.identityHashCode(this)}", "unpin $id")
    // do nothing
  }
}