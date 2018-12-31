package com.takusemba.jethub.api

import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.SimpleDeveloper
import org.threeten.bp.LocalDateTime

interface SearchApi {

  suspend fun getHotRepos(language: Language, from: LocalDateTime): List<Repository>

  suspend fun getHotDevelopers(language: Language, from: LocalDateTime): List<SimpleDeveloper>

  suspend fun searchRepos(query: String): List<Repository>

  suspend fun searchDevelopers(query: String): List<SimpleDeveloper>
}