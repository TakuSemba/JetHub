package com.takusemba.jethub.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class TestDatabaseModule {

  @Provides
  @Singleton
  fun provideRepoDb(): RepoDb {
    return FakeRepoDb()
  }
}
