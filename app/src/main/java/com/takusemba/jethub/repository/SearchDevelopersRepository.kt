package com.takusemba.jethub.repository

import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.model.SimpleDeveloper
import javax.inject.Inject

class SearchDevelopersRepository @Inject constructor(
  private val searchApi: SearchApi
) {

  suspend fun searchDevelopers(query: String): List<SimpleDeveloper> {
    return searchApi.searchDevelopers(query)
  }
}