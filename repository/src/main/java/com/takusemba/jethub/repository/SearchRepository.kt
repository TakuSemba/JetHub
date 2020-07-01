package com.takusemba.jethub.repository

import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.SimpleDeveloper
import javax.inject.Inject

/**
 * Repository for searching
 */
class SearchRepository @Inject constructor(
  private val searchApi: SearchApi
) {

  suspend fun searchRepos(query: String): List<Repository> {
    return searchApi.searchRepos(query)
  }

  suspend fun searchDevelopers(query: String): List<SimpleDeveloper> {
    return searchApi.searchDevelopers(query)
  }
}