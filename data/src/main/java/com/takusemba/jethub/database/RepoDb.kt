package com.takusemba.jethub.database

import com.takusemba.jethub.model.Repo

/**
 * Repository DB
 */
interface RepoDb {

  /**
   * get all [Repo]
   */
  suspend fun getAll(): List<Repo>

  /**
   * insert [Repo]
   */
  suspend fun insert(repo: Repo)

  /**
   * delete [Repo]
   */
  suspend fun delete(repo: Repo)

  /**
   * delete all [Repo]
   */
  suspend fun deleteAll()
}
