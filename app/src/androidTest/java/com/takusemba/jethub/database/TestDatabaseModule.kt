package com.takusemba.jethub.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestDatabaseModule {

  @Provides
  @Singleton
  fun provideRepoDb(): RepoDb {
    return FakeRepoDb()
  }
}
