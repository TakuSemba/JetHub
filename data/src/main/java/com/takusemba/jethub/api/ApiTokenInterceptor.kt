package com.takusemba.jethub.api

import com.takusemba.jethub.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to add API token to a Authorization header.
 */
class ApiTokenInterceptor : Interceptor {

  companion object {

    private const val AUTHORIZATION_HEADER = "Authorization"
  }

  override fun intercept(chain: Interceptor.Chain): Response {
    val token = BuildConfig.GITHUB_API_TOKEN
    val builder = chain.request().newBuilder()
    // GITHUB_API_TOKEN will be `null` if nothing is set in local.properties
    if (token.isNotBlank() && token != "null") {
      builder.addHeader(AUTHORIZATION_HEADER, "token $token")
    }
    return chain.proceed(builder.build())
  }
}
