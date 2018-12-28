package com.takusemba.jethub.api

import com.takusemba.jethub.model.Repository

interface RepoApi {

  suspend fun getRepos(owner: String): List<Repository>
}