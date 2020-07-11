package com.takusemba.jethub.database

import com.takusemba.jethub.model.Repo

class FakeRepoDb : RepoDb {

  override suspend fun getAll(): List<Repo> = emptyList()

  override suspend fun insert(repo: Repo) = Unit

  override suspend fun delete(repo: Repo) = Unit

  override suspend fun deleteAll() = Unit
}