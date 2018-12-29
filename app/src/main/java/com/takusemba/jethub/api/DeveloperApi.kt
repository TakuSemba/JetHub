package com.takusemba.jethub.api

import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repository

interface DeveloperApi {

  suspend fun getDeveloper(name: String): Developer

  suspend fun getRepos(owner: String): List<Repository>
}