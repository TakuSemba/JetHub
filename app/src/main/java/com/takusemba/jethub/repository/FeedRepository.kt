package com.takusemba.jethub.repository

import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.api.SearchApiClient
import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.User
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

class FeedRepository @Inject constructor(
  private val searchApi: SearchApi
) {

  suspend fun getHotRepositories(): List<Repository> {
    return searchApi.getHotRepositories(Language.KOTLIN, LocalDateTime.now().minusMonths(1))
  }

  suspend fun getHotUsers(): List<User> {
    return searchApi.getHotUsers(Language.KOTLIN, LocalDateTime.now().minusMonths(1))
  }
}