package com.takusemba.jethub.database

import com.takusemba.jethub.model.SimpleRepository

interface RepoDb {

  suspend fun getAll(): List<SimpleRepository>

  suspend fun insert(repository: SimpleRepository)

  suspend fun delete(repository: SimpleRepository)

  suspend fun deleteAll()
}