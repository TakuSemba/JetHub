package com.takusemba.jethub.api

import com.takusemba.jethub.api.response.DeveloperResponse
import com.takusemba.jethub.api.response.RepositoryResponse
import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Developer API client
 */
class DeveloperApiClient(retrofit: Retrofit) : DeveloperApi {

  interface Service {

    @GET("users/{name}")
    suspend fun getDeveloper(@Path("name") name: String): DeveloperResponse

    @GET("users/{owner}/repos")
    suspend fun getRepos(@Path("owner") owner: String): List<RepositoryResponse>
  }

  private val service = retrofit.create(Service::class.java)

  override suspend fun getDeveloper(name: String): Developer {
    return withContext(IO) {
      service.getDeveloper(name).toModel()
    }
  }

  override suspend fun getRepos(owner: String): List<Repo> {
    return withContext(IO) {
      service.getRepos(owner).map { response -> response.toModel() }
    }
  }
}
