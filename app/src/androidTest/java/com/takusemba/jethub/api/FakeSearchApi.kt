package com.takusemba.jethub.api

import com.takusemba.jethub.model.Owner
import com.takusemba.jethub.model.Repo
import java.time.LocalDateTime

class FakeSearchApi : SearchApi {

  override suspend fun getHotRepos(language: String, from: LocalDateTime): List<Repo> {
    return emptyList()
  }

  override suspend fun getHotOwners(language: String, from: LocalDateTime): List<Owner> {
    return emptyList()
  }

  override suspend fun searchRepos(query: String): List<Repo> {
    return emptyList()
  }

  override suspend fun searchOwners(query: String): List<Owner> {
    return emptyList()
  }
}