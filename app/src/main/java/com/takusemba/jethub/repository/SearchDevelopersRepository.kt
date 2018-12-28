package com.takusemba.jethub.repository

import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.SimpleDeveloper
import javax.inject.Inject

class SearchDevelopersRepository @Inject constructor(
  private val searchApi: SearchApi
) {

  // TODO 検索結果をkotlinに縛らないようにする
  suspend fun searchUsers(query: String): List<SimpleDeveloper> {
    return searchApi.searchUsers(Language.KOTLIN, query)
  }
}