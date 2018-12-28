package com.takusemba.jethub.api

import com.takusemba.jethub.api.response.ListResponse
import com.takusemba.jethub.api.response.RepositoryResponse
import com.takusemba.jethub.api.response.UserResponse
import com.takusemba.jethub.model.DateFormatters
import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.User
import kotlinx.coroutines.Deferred
import org.threeten.bp.LocalDateTime
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class SearchApiClient(retrofit: Retrofit) : SearchApi {

  interface Service {

    @GET("search/repositories")
    fun getHotRepos(
      @Query("q") query: String,
      @Query("sort") sort: String = "stars"
    ): Deferred<ListResponse<RepositoryResponse>>

    @GET("search/users")
    fun getHotUsers(
      @Query("q") query: String,
      @Query("sort") sort: String = "stars"
    ): Deferred<ListResponse<UserResponse>>

    @GET("search/repositories")
    fun searchRepos(
      @Query("q") query: String
    ): Deferred<ListResponse<RepositoryResponse>>

    @GET("search/users")
    fun searchUsers(
      @Query("q") query: String
    ): Deferred<ListResponse<UserResponse>>
  }

  private val service = retrofit.create(Service::class.java)

  override suspend fun getHotRepos(
    language: Language,
    from: LocalDateTime
  ): List<Repository> {
    return service.getHotRepos("language:${language.name}+created:>${from.format(DateFormatters.ofSearchQuery())}")
      .await()
      .items
      ?.map { response -> response.toModel() } ?: emptyList()
  }

  override suspend fun getHotUsers(
    language: Language,
    from: LocalDateTime
  ): List<User> {
    return service.getHotUsers("language:${language.name}+created:>${from.format(DateFormatters.ofSearchQuery())}")
      .await()
      .items
      ?.map { response -> response.toModel() } ?: emptyList()
  }

  override suspend fun searchRepos(language: Language, query: String): List<Repository> {
    return service.searchRepos("$query+language:${language.name}")
      .await()
      .items
      ?.map { response -> response.toModel() } ?: emptyList()
  }

  override suspend fun searchUsers(language: Language, query: String): List<User> {
    return service.searchUsers("$query+language:${language.name}")
      .await()
      .items
      ?.map { response -> response.toModel() } ?: emptyList()
  }
}