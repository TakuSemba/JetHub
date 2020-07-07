package com.takusemba.jethub.repository

import com.takusemba.jethub.api.DeveloperApi
import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repository
import javax.inject.Inject

/**
 * Repository for Developer
 */
class DeveloperRepository @Inject constructor(
  private val developerApi: DeveloperApi
) {

  suspend fun getDeveloper(name: String): Developer {
    return developerApi.getDeveloper(name)
  }

  suspend fun getDeveloperRepos(owner: String): List<Repository> {
    return developerApi.getRepos(owner)
  }
}
