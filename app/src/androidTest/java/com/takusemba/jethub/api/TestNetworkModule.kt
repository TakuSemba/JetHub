package com.takusemba.jethub.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class TestNetworkModule {

  @Singleton
  @Provides
  fun provideRepoApi(): RepoApi {
    return FakeRepoApi()
  }

  @Singleton
  @Provides
  fun provideUserApi(): DeveloperApi {
    return FakeDeveloperApi()
  }

  @Singleton
  @Provides
  fun provideSearchApi(): SearchApi {
    return FakeSearchApi()
  }
}
