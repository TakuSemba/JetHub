package com.takusemba.jethub.api

import com.takusemba.jethub.model.ReadMe
import com.takusemba.jethub.model.Repo

/**
 * Repository API
 */
interface RepoApi {

  /**
   * get [Repo] from owner name and repo name.
   */
  suspend fun getRepo(owner: String, repo: String): Repo

  /**
   * get [ReadMe] from owner name and repo name.
   */
  suspend fun getReadMe(owner: String, repo: String): ReadMe
}
