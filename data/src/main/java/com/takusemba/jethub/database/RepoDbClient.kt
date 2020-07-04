package com.takusemba.jethub.database

import com.takusemba.jethub.database.dao.RepositoryDao
import com.takusemba.jethub.database.entity.RepositoryEntity
import com.takusemba.jethub.model.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * Repository DB client
 */
class RepoDbClient(private val repositoryDao: RepositoryDao) : RepoDb {

  override suspend fun getAll(): List<Repository> {
    return withContext(IO) {
      repositoryDao.getAll().map { entity -> entity.toModel() }
    }
  }

  override suspend fun insert(repository: Repository) {
    withContext(IO) {
      repositoryDao.insert(RepositoryEntity.fromModel(repository))
    }
  }

  override suspend fun delete(repository: Repository) {
    withContext(IO) {
      repositoryDao.delete(RepositoryEntity.fromModel(repository))
    }
  }

  override suspend fun deleteAll() {
    withContext(IO) {
      repositoryDao.deleteAll()
    }
  }
}