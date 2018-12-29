package com.takusemba.jethub.api

import com.takusemba.jethub.model.Repository

interface RepoApi {

  suspend fun getRepo(owner: String, repo: String): Repository
}