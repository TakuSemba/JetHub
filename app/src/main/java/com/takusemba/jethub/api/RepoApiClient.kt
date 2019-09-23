package com.takusemba.jethub.api

import com.takusemba.jethub.api.response.RepositoryResponse
import com.takusemba.jethub.model.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Repository API client
 */
class RepoApiClient(retrofit: Retrofit) : RepoApi {

  interface Service {

    @GET("repos/{owner}/{repo}")
    fun getRepo(@Path("owner") owner: String, @Path("repo") repo: String): RepositoryResponse
  }

  private val service = retrofit.create(Service::class.java)

  override suspend fun getRepo(owner: String, repo: String): Repository {
    return withContext(IO) {
      service.getRepo(owner, repo).toModel()
    }
  }
}