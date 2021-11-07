package com.takusemba.jethub.repository

import com.takusemba.jethub.api.RepoApi
import com.takusemba.jethub.database.RepoDb
import com.takusemba.jethub.model.ReadMe
import com.takusemba.jethub.model.Repo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for Repo
 */
@Singleton
class RepoRepository @Inject constructor(
  private val repoDb: RepoDb,
  private val repoApi: RepoApi
) {

  suspend fun getRepo(owner: String, repo: String): Repo {
    return repoApi.getRepo(owner, repo)
  }

  suspend fun getReadMe(owner: String, repo: String): ReadMe {
    return repoApi.getReadMe(owner, repo)
  }

  suspend fun pin(repo: Repo) {
    repoDb.insert(repo)
  }

  suspend fun unpin(repo: Repo) {
    repoDb.delete(repo)
  }

  suspend fun findAllPins(): List<Repo> {
    return repoDb.getAll()
  }
}
