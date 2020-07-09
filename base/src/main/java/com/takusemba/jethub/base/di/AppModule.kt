package com.takusemba.jethub.base.di

import android.app.Application
import androidx.recyclerview.widget.RecyclerView
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
  fun provideErrorHandler(context: Application): ErrorHandler {
    return AppErrorHandler(context)
  }

  @Singleton
  @Provides
  fun provideRecycledViewPool(): RecyclerView.RecycledViewPool {
    return RecyclerView.RecycledViewPool()
  }
}
