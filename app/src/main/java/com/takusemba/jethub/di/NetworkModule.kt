package com.takusemba.jethub.di

import com.takusemba.jethub.Config
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
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

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