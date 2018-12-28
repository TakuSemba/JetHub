package com.takusemba.jethub.repository

import com.takusemba.jethub.api.DeveloperApi
import com.takusemba.jethub.api.RepoApi
import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repository
import javax.inject.Inject

class DeveloperDetailRepository @Inject constructor(
  private val developerApi: DeveloperApi,
  private val repoApi: RepoApi
) {

  suspend fun getDeveloper(name: String): Developer {
    return developerApi.getDeveloper(name)
  }

  suspend fun getRepos(owner: String): List<Repository> {
    return repoApi.getRepos(owner)
  }
}