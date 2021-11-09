package com.takusemba.jethub.repository

import com.takusemba.jethub.api.DeveloperApi
import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeveloperRepository @Inject constructor(
  private val developerApi: DeveloperApi
) {

  suspend fun getDeveloper(name: String): Developer {
    return developerApi.getDeveloper(name)
  }

  suspend fun getDeveloperRepos(owner: String): List<Repo> {
    return developerApi.getRepos(owner)
  }
}
