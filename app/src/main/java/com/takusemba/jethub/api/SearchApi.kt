package com.takusemba.jethub.api

import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.User
import org.threeten.bp.LocalDateTime

interface SearchApi {

  suspend fun getHotRepositories(language: Language, from: LocalDateTime): List<Repository>

  suspend fun getHotUsers(language: Language, from: LocalDateTime): List<User>
}