package com.takusemba.jethub.database

import com.takusemba.jethub.model.SimpleRepository

/**
 * Repository DB
 */
interface RepoDb {

  /**
   * get all [SimpleRepository]
   */
  suspend fun getAll(): List<SimpleRepository>

  /**
   * insert [SimpleRepository]
   */
  suspend fun insert(repository: SimpleRepository)

  /**
   * delete [SimpleRepository]
   */
  suspend fun delete(repository: SimpleRepository)

  /**
   * delete all [SimpleRepository]
   */
  suspend fun deleteAll()
}