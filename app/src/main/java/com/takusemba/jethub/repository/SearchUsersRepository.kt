package com.takusemba.jethub.repository

import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.SimpleUser
import javax.inject.Inject

class SearchUsersRepository @Inject constructor(
  private val searchApi: SearchApi
) {

  suspend fun searchUsers(query: String): List<SimpleUser> {
    return searchApi.searchUsers(Language.KOTLIN, query)
  }
}