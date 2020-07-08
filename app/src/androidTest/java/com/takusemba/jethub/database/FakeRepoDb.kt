package com.takusemba.jethub.database

import com.takusemba.jethub.model.Repository

class FakeRepoDb : RepoDb {

  override suspend fun getAll(): List<Repository> = emptyList()

  override suspend fun insert(repository: Repository) = Unit

  override suspend fun delete(repository: Repository) = Unit

  override suspend fun deleteAll() = Unit
}