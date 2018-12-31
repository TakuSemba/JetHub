package com.takusemba.jethub.repository

import com.takusemba.jethub.api.RepoApi
import com.takusemba.jethub.database.RepoDb
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.SimpleRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val repoDb: RepoDb,
  private val repoApi: RepoApi
) {

  suspend fun pin(repository: Repository) {
    repoDb.insert(SimpleRepository(repository.id, repository.name, repository.owner.login))
  }

  suspend fun unpin(repository: Repository) {
    repoDb.delete(SimpleRepository(repository.id, repository.name, repository.owner.login))
  }

  suspend fun findAll(): List<Repository> {
    return coroutineScope {
      repoDb.getAll()
        .map { entity -> SimpleRepository(entity.id, entity.name, entity.owner) }
        .map { repo -> async { repoApi.getRepo(repo.owner, repo.name) } }
        .awaitAll()
    }
  }
}