package com.takusemba.jethub.api

import com.takusemba.jethub.api.response.DeveloperResponse
import com.takusemba.jethub.model.Developer
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class DeveloperApiClient(retrofit: Retrofit) : DeveloperApi {

  interface Service {

    @GET("users/{name}")
    fun getDeveloper(
      @Path("name") name: String
    ): Deferred<DeveloperResponse>
  }

  private val service = retrofit.create(Service::class.java)

  override suspend fun getDeveloper(name: String): Developer {
    return service.getDeveloper(name).await().toModel()
  }
}