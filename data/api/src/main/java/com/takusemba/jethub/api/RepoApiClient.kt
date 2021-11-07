package com.takusemba.jethub.api

import com.takusemba.jethub.api.response.ReadMeResponse
import com.takusemba.jethub.api.response.RepositoryResponse
import com.takusemba.jethub.model.ReadMe
import com.takusemba.jethub.model.Repo
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
    suspend fun getRepo(
      @Path("owner") owner: String,
      @Path("repo") repo: String
    ): RepositoryResponse

    @GET("repos/{owner}/{repo}/readme")
    suspend fun getReadMe(
      @Path("owner") owner: String,
      @Path("repo") repo: String
    ): ReadMeResponse
  }

  private val service = retrofit.create(Service::class.java)

  override suspend fun getRepo(owner: String, repo: String): Repo {
    return withContext(IO) {
      service.getRepo(owner, repo).toModel()
    }
  }

  override suspend fun getReadMe(owner: String, repo: String): ReadMe {
    return withContext(IO) {
      service.getReadMe(owner, repo).toModel()
    }
  }
}
