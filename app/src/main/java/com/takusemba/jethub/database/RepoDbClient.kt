package com.takusemba.jethub.database

import com.takusemba.jethub.database.dao.RepositoryDao
import com.takusemba.jethub.database.entity.RepositoryEntity
import com.takusemba.jethub.model.SimpleRepository

class RepoDbClient(private val repositoryDao: RepositoryDao) : RepoDb {

  override fun getAll(): List<SimpleRepository> {
    return repositoryDao.getAll()
      .map { entity -> SimpleRepository(entity.id, entity.name, entity.owner) }
  }

  override fun insert(repository: SimpleRepository) {
    repositoryDao.insert(RepositoryEntity(repository.id, repository.name, repository.owner))
  }

  override fun delete(repository: SimpleRepository) {
    repositoryDao.delete(RepositoryEntity(repository.id, repository.name, repository.owner))
  }

  override fun deleteAll() {
    repositoryDao.deleteAll()
  }
}