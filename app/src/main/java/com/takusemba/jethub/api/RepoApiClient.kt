package com.takusemba.jethub.api

import com.takusemba.jethub.api.response.ListResponse
import com.takusemba.jethub.api.response.RepositoryResponse
import com.takusemba.jethub.model.Repository
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class RepoApiClient(retrofit: Retrofit) : RepoApi {

  interface Service {

    @GET("users/{owner}/repos")
    fun getRepos(
      @Path("owner") owner: String
    ): Deferred<ListResponse<RepositoryResponse>>
  }

  private val service = retrofit.create(Service::class.java)

  override suspend fun getRepos(owner: String): List<Repository> {
    return service.getRepos(owner)
      .await()
      .items
      ?.map { response -> response.toModel() } ?: emptyList()
  }
}