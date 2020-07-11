package com.takusemba.jethub.api

import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repo

/**
 * Developer API
 */
interface DeveloperApi {

  /**
   * get [Developer] from name.
   */
  suspend fun getDeveloper(name: String): Developer

  /**
   * get list of [Repo] of owner.
   */
  suspend fun getRepos(owner: String): List<Repo>
}
