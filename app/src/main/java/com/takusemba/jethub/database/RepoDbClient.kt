package com.takusemba.jethub.database

import com.takusemba.jethub.database.dao.RepositoryDao
import com.takusemba.jethub.database.entity.RepositoryEntity
import com.takusemba.jethub.model.SimpleRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * Repository DB client
 */
class RepoDbClient(private val repositoryDao: RepositoryDao) : RepoDb {

  override suspend fun getAll(): List<SimpleRepository> {
    return withContext(IO) {
      repositoryDao.getAll()
        .map { entity -> SimpleRepository(entity.id, entity.name, entity.owner) }
    }
  }

  override suspend fun insert(repository: SimpleRepository) {
    withContext(IO) {
      repositoryDao.insert(RepositoryEntity(repository.id, repository.name, repository.owner))
    }
  }

  override suspend fun delete(repository: SimpleRepository) {
    withContext(IO) {
      repositoryDao.delete(RepositoryEntity(repository.id, repository.name, repository.owner))
    }
  }

  override suspend fun deleteAll() {
    withContext(IO) {
      repositoryDao.deleteAll()
    }
  }
}