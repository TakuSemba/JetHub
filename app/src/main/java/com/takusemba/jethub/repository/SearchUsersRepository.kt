package com.takusemba.jethub.repository

import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.User
import javax.inject.Inject

class SearchUsersRepository @Inject constructor(
  private val searchApi: SearchApi
) {

  suspend fun searchUsers(query: String): List<User> {
    return searchApi.searchUsers(query)
  }
}