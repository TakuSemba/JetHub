package com.takusemba.jethub.api

import com.takusemba.jethub.model.Owner
import com.takusemba.jethub.model.Repository
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
   * get hot list of [Owner] from [language] created after [from].
   */
  suspend fun getHotOwners(language: String, from: LocalDateTime): List<Owner>

  /**
   * search [Repository] from query.
   */
  suspend fun searchRepos(query: String): List<Repository>

  /**
   * search [Owner] from query.
   */
  suspend fun searchOwners(query: String): List<Owner>
}