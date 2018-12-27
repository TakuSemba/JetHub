package com.takusemba.jethub.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.takusemba.jethub.Config
import com.takusemba.jethub.api.RepositoryApi
import com.takusemba.jethub.api.RepositoryApiClient
import com.takusemba.jethub.api.SearchApi
import com.takusemba.jethub.api.SearchApiClient
import com.takusemba.jethub.api.UserApi
import com.takusemba.jethub.api.UserApiClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(10L, TimeUnit.SECONDS)
      .writeTimeout(10L, TimeUnit.SECONDS)
      .readTimeout(30L, TimeUnit.SECONDS)
      .build()
  }

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("${Config.API_ENDPOINT}/")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build()
  }

  @Singleton
  @Provides
  fun provideRepositoryApi(retrofit: Retrofit): RepositoryApi {
    return RepositoryApiClient(retrofit)
  }

  @Singleton
  @Provides
  fun provideUserApi(retrofit: Retrofit): UserApi {
    return UserApiClient(retrofit)
  }

  @Singleton
  @Provides
  fun provideSearchApi(retrofit: Retrofit): SearchApi {
    return SearchApiClient(retrofit)
  }
}