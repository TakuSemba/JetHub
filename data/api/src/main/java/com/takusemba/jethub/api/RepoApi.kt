package com.takusemba.jethub.api

import com.takusemba.jethub.model.Repo

/**
 * Repository API
 */
interface RepoApi {

  /**
   * get [Repo] from owner name and repo name.
   */
  suspend fun getRepo(owner: String, repo: String): Repo
}
