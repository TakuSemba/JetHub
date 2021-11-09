package com.takusemba.jethub.repository

import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.model.Owner
import com.takusemba.jethub.model.Repo
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
  private val searchApi: SearchApi
) {

  suspend fun searchHotRepos(language: String): List<Repo> {
    return searchApi.getHotRepos(language, LocalDateTime.now().minusMonths(1))
  }

  suspend fun searchRepos(query: String): List<Repo> {
    return searchApi.searchRepos(query)
  }

  suspend fun searchOwners(query: String): List<Owner> {
    return searchApi.searchOwners(query)
  }
}
