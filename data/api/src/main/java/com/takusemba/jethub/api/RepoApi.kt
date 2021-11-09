package com.takusemba.jethub.api

import com.takusemba.jethub.model.ReadMe
import com.takusemba.jethub.model.Repo

interface RepoApi {

  suspend fun getRepo(owner: String, repo: String): Repo

  suspend fun getReadMe(owner: String, repo: String): ReadMe
}
