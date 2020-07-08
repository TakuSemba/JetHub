package com.takusemba.jethub.api

import com.takusemba.jethub.model.Repository

class FakeRepoApi : RepoApi {

  override suspend fun getRepo(owner: String, repo: String): Repository {
    return Repository.createRepository()
  }
}