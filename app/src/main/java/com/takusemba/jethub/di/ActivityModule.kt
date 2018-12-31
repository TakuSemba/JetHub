package com.takusemba.jethub.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import dagger.Binds
import dagger.Module

/**
 * Builder for activity.
 * Define all the classes that need to be provided in the scope of Activity.
 */
@Module
interface ActivityModule {

  @Binds
  fun provideActivity(activity: AppCompatActivity): Activity

  @Binds
  fun provideLifecycleOwner(activity: AppCompatActivity): LifecycleOwner
}
