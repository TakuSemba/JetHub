package com.takusemba.jethub.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class DebugNetworkModule : NetworkModule() {

  override fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(10L, TimeUnit.SECONDS)
      .writeTimeout(10L, TimeUnit.SECONDS)
      .readTimeout(30L, TimeUnit.SECONDS)
      .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
      })
      .build()
  }
}