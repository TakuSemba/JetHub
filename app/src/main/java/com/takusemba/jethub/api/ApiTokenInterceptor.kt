package com.takusemba.jethub.api

import com.takusemba.jethub.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiTokenInterceptor : Interceptor {

  companion object {

    private const val AUTHORIZATION_HEADER = "Authorization"
  }

  override fun intercept(chain: Interceptor.Chain): Response {
    val builder = chain.request().newBuilder()
    builder.addHeader(AUTHORIZATION_HEADER, "token ${BuildConfig.API_KEY}")
    return chain.proceed(builder.build())
  }
}
