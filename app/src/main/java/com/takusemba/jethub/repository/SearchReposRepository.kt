package com.takusemba.jethub.repository

import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.Repository
import javax.inject.Inject

class SearchReposRepository @Inject constructor(
  private val searchApi: SearchApi
) {

  // TODO 検索結果をkotlinに縛らないようにする
  suspend fun searchRepos(query: String): List<Repository> {
    return searchApi.searchRepos(Language.KOTLIN, query)
  }
}