package com.takusemba.jethub.api

import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repo

class FakeDeveloperApi : DeveloperApi {

  override suspend fun getDeveloper(name: String): Developer {
    return Developer.createDeveloper()
  }

  override suspend fun getRepos(owner: String): List<Repo> {
    return emptyList()
  }
}