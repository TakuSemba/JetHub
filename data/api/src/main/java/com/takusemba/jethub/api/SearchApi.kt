package com.takusemba.jethub.api

import com.takusemba.jethub.model.Owner
import com.takusemba.jethub.model.Repo
import java.time.LocalDateTime

interface SearchApi {

  suspend fun getHotRepos(language: String, from: LocalDateTime): List<Repo>

  suspend fun getHotOwners(language: String, from: LocalDateTime): List<Owner>

  suspend fun searchRepos(query: String): List<Repo>

  suspend fun searchOwners(query: String): List<Owner>
}
