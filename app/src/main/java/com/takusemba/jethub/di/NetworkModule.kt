package com.takusemba.jethub.di

import com.takusemba.jethub.Config
import com.takusemba.jethub.api.ApiTokenInterceptor
import com.takusemba.jethub.api.DeveloperApi
import com.takusemba.jethub.api.DeveloperApiClient
import com.takusemba.jethub.api.RepoApi
import com.takusemba.jethub.api.RepoApiClient
import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.api.SearchApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Database Module
 * Define all the network-related classes that need to be provided in the scope of Application.
 */
@Module
@InstallIn(ApplicationComponent::class)
open class NetworkModule {

  @Provides
  @Singleton
  open fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(10L, TimeUnit.SECONDS)
      .writeTimeout(10L, TimeUnit.SECONDS)
      .readTimeout(30L, TimeUnit.SECONDS)
      .addInterceptor(ApiTokenInterceptor())
      .build()
  }

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(Config.API_ENDPOINT)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton
  @Provides
  fun provideRepositoryApi(retrofit: Retrofit): RepoApi {
    return RepoApiClient(retrofit)
  }

  @Singleton
  @Provides
  fun provideUserApi(retrofit: Retrofit): DeveloperApi {
    return DeveloperApiClient(retrofit)
  }

  @Singleton
  @Provides
  fun provideSearchApi(retrofit: Retrofit): SearchApi {
    return SearchApiClient(retrofit)
  }
}