package com.takusemba.jethub.api

import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.SimpleDeveloper
import java.time.LocalDateTime

/**
 * Search API
 */
interface SearchApi {

  /**
   * get hot list of [Repository] from [Language] created after [from].
   */
  suspend fun getHotRepos(language: Language, from: LocalDateTime): List<Repository>

  /**
   * get hot list of [SimpleDeveloper] from [Language] created after [from].
   */
  suspend fun getHotDevelopers(language: Language, from: LocalDateTime): List<SimpleDeveloper>

  /**
   * search [Repository] from query.
   */
  suspend fun searchRepos(query: String): List<Repository>

  /**
   * search [SimpleDeveloper] from query.
   */
  suspend fun searchDevelopers(query: String): List<SimpleDeveloper>
}