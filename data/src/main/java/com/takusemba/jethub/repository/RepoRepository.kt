package com.takusemba.jethub.repository

import com.takusemba.jethub.api.RepoApi
import com.takusemba.jethub.database.RepoDb
import com.takusemba.jethub.model.Repository
import javax.inject.Inject

/**
 * Repository for Repo
 */
class RepoRepository @Inject constructor(
  private val repoDb: RepoDb,
  private val repoApi: RepoApi
) {

  suspend fun getRepo(owner: String, repo: String): Repository {
    return repoApi.getRepo(owner, repo)
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
