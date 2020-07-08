package com.takusemba.jethub.api

import com.takusemba.jethub.model.Owner
import com.takusemba.jethub.model.Repository
import java.time.LocalDateTime

class FakeSearchApi : SearchApi {

  override suspend fun getHotRepos(language: String, from: LocalDateTime): List<Repository> {
    return emptyList()
  }

  override suspend fun getHotOwners(language: String, from: LocalDateTime): List<Owner> {
    return emptyList()
  }

  override suspend fun searchRepos(query: String): List<Repository> {
    return emptyList()
  }

  override suspend fun searchOwners(query: String): List<Owner> {
    return emptyList()
  }
}