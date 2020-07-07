package com.takusemba.jethub.di.api

import com.takusemba.jethub.api.ApiTokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Database Module for debugging.
 */
@Module
@InstallIn(ApplicationComponent::class)
class AdditionalNetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(10L, TimeUnit.SECONDS)
      .writeTimeout(10L, TimeUnit.SECONDS)
      .readTimeout(30L, TimeUnit.SECONDS)
      .addNetworkInterceptor(
        HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.HEADERS
        }
      )
      .addInterceptor(ApiTokenInterceptor())
      .build()
  }
}
