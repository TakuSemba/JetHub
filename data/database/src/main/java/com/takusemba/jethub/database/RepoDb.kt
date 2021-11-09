package com.takusemba.jethub.database

import com.takusemba.jethub.model.Repo

interface RepoDb {

  suspend fun getAll(): List<Repo>

  suspend fun insert(repo: Repo)

  suspend fun delete(repo: Repo)

  suspend fun deleteAll()
}
