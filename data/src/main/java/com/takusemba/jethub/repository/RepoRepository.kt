package com.takusemba.jethub.repository

import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.database.RepoDb
import com.takusemba.jethub.model.Repository
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * Repository for Repo
 */
class RepoRepository @Inject constructor(
  private val searchApi: SearchApi,
  private val repoDb: RepoDb
) {

  suspend fun getHotRepos(language: String): List<Repository> {
    return searchApi.getHotRepos(language, LocalDateTime.now().minusMonths(1))
  }

  suspend fun pin(repository: Repository) {
    repoDb.insert(repository)
  }

  suspend fun unpin(repository: Repository) {
    repoDb.delete(repository)
  }

  suspend fun findAllPins(): List<Repository> {
    return repoDb.getAll()
  }
}