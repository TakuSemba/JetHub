package com.takusemba.jethub.base.di

import android.content.Context
import com.takusemba.jethub.base.AppErrorHandler
import com.takusemba.jethub.base.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

  @Singleton
  @Provides
  fun provideErrorHandler(context: Context): ErrorHandler {
    return AppErrorHandler(context)
  }
}