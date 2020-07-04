package com.takusemba.jethub.api

import com.takusemba.jethub.api.response.ListResponse
import com.takusemba.jethub.api.response.RepositoryResponse
import com.takusemba.jethub.api.response.OwnerResponse
import com.takusemba.jethub.model.DateFormatters
import com.takusemba.jethub.model.Owner
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.SimpleDeveloper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDateTime

/**
 * Search API client
 */
class SearchApiClient(retrofit: Retrofit) : SearchApi {

  interface Service {

    @GET("search/repositories")
    suspend fun getHotRepos(
      @Query("q") query: String,
      @Query("sort") sort: String = "stars"
    ): ListResponse<RepositoryResponse>

    @GET("search/users")
    suspend fun getHotDevelopers(
      @Query("q") query: String,
      @Query("sort") sort: String = "stars"
    ): ListResponse<OwnerResponse>

    @GET("search/repositories")
    suspend fun searchRepos(@Query("q") query: String): ListResponse<RepositoryResponse>

    @GET("search/users")
    suspend fun searchDevelopers(@Query("q") query: String): ListResponse<OwnerResponse>
  }

  private val service = retrofit.create(Service::class.java)

  override suspend fun getHotRepos(
    language: String,
    from: LocalDateTime
  ): List<Repository> {
    return withContext(IO) {
      service.getHotRepos(
        "language:${language} created:>${from.format(DateFormatters.ofSearchQuery())}")
        .items
        ?.map { response -> response.toModel() } ?: emptyList()
    }
  }

  override suspend fun getHotOwners(
    language: String,
    from: LocalDateTime
  ): List<Owner> {
    return withContext(IO) {
      service.getHotDevelopers(
        "language:${language} created:>${from.format(DateFormatters.ofSearchQuery())}")
        .items
        ?.map { response -> response.toModel() } ?: emptyList()
    }
  }

  override suspend fun searchRepos(query: String): List<Repository> {
    return withContext(IO) {
      val q = if (query.isNotBlank()) {
        query
      } else {
        "created:>${LocalDateTime.now().minusDays(7).format(DateFormatters.ofSearchQuery())}"
      }
      service.searchRepos(q)
        .items
        ?.map { response -> response.toModel() } ?: emptyList()
    }
  }

  override suspend fun searchOwners(query: String): List<Owner> {
    return withContext(IO) {
      val q = if (query.isNotBlank()) {
        query
      } else {
        "created:>${LocalDateTime.now().minusDays(7).format(DateFormatters.ofSearchQuery())}"
      }
      service.searchDevelopers(q)
        .items
        ?.map { response -> response.toModel() } ?: emptyList()
    }
  }
}