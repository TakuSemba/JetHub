package com.takusemba.jethub.database

import com.takusemba.jethub.model.SimpleRepository

interface RepoDb {

  fun getAll(): List<SimpleRepository>

  fun insert(repository: SimpleRepository)

  fun delete(repository: SimpleRepository)

  fun deleteAll()
}