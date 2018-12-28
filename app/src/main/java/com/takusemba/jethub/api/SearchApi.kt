package com.takusemba.jethub.api

import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.SimpleDeveloper
import org.threeten.bp.LocalDateTime

interface SearchApi {

  suspend fun getHotRepos(language: Language, from: LocalDateTime): List<Repository>

  suspend fun getHotUsers(language: Language, from: LocalDateTime): List<SimpleDeveloper>

  suspend fun searchRepos(language: Language, query: String): List<Repository>

  suspend fun searchUsers(language: Language, query: String): List<SimpleDeveloper>
}