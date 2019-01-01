package com.takusemba.jethub.api

import com.takusemba.jethub.BuildConfig
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
    val token = BuildConfig.API_KEY
    val builder = chain.request().newBuilder()
    if (token.isNotBlank() && token != "null") {
      builder.addHeader(AUTHORIZATION_HEADER, "token ${BuildConfig.API_KEY}")
    }
    return chain.proceed(builder.build())
  }
}
