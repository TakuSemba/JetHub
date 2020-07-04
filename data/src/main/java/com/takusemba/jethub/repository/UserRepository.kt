package com.takusemba.jethub.repository

import com.takusemba.jethub.database.RepoDb
import com.takusemba.jethub.model.Repository
import javax.inject.Inject

/**
 * Repository for user
 */
class UserRepository @Inject constructor(
  private val repoDb: RepoDb
) {

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