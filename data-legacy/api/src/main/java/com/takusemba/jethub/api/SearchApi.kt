package com.takusemba.jethub.api

import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.SimpleDeveloper
import java.time.LocalDateTime

/**
 * Search API
 */
interface SearchApi {

  /**
   * get hot list of [Repository] from [language] created after [from].
   */
  suspend fun getHotRepos(language: String, from: LocalDateTime): List<Repository>

  /**
   * get hot list of [SimpleDeveloper] from [language] created after [from].
   */
  suspend fun getHotDevelopers(language: String, from: LocalDateTime): List<SimpleDeveloper>

  /**
   * search [Repository] from query.
   */
  suspend fun searchRepos(query: String): List<Repository>

  /**
   * search [SimpleDeveloper] from query.
   */
  suspend fun searchDevelopers(query: String): List<SimpleDeveloper>
}