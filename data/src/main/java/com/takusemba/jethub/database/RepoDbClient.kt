package com.takusemba.jethub.database

import com.takusemba.jethub.database.dao.RepositoryDao
import com.takusemba.jethub.database.entity.RepositoryEntity
import com.takusemba.jethub.model.Repo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * Repository DB client
 */
class RepoDbClient(private val repositoryDao: RepositoryDao) : RepoDb {

  override suspend fun getAll(): List<Repo> {
    return withContext(IO) {
      repositoryDao.getAll().map { entity -> entity.toModel() }
    }
  }

  override suspend fun insert(repo: Repo) {
    withContext(IO) {
      repositoryDao.insert(RepositoryEntity.fromModel(repo))
    }
  }

  override suspend fun delete(repo: Repo) {
    withContext(IO) {
      repositoryDao.delete(RepositoryEntity.fromModel(repo))
    }
  }

  override suspend fun deleteAll() {
    withContext(IO) {
      repositoryDao.deleteAll()
    }
  }
}
