package com.takusemba.jethub.api

import com.takusemba.jethub.model.Repository

/**
 * Repository API
 */
interface RepoApi {

  /**
   * get [Repository] from owner name and repo name.
   */
  suspend fun getRepo(owner: String, repo: String): Repository
}
