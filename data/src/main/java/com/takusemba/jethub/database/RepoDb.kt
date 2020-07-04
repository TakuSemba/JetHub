package com.takusemba.jethub.database

import com.takusemba.jethub.model.Repository

/**
 * Repository DB
 */
interface RepoDb {

  /**
   * get all [Repository]
   */
  suspend fun getAll(): List<Repository>

  /**
   * insert [Repository]
   */
  suspend fun insert(repository: Repository)

  /**
   * delete [Repository]
   */
  suspend fun delete(repository: Repository)

  /**
   * delete all [Repository]
   */
  suspend fun deleteAll()
}