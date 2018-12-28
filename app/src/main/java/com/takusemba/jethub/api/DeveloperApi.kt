package com.takusemba.jethub.api

import com.takusemba.jethub.model.Developer

interface DeveloperApi {

  suspend fun getDeveloper(name: String): Developer
}