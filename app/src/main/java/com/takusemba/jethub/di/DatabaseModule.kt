package com.takusemba.jethub.di

import android.app.Application
import androidx.room.Room
import com.takusemba.jethub.database.AppDatabase
import com.takusemba.jethub.database.RepoDb
import com.takusemba.jethub.database.RepoDbClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "database-name").build()
  }

  @Provides
  @Singleton
  fun provideRepoDb(appDatabase: AppDatabase): RepoDb {
    return RepoDbClient(appDatabase.repositoryDao)
  }
}