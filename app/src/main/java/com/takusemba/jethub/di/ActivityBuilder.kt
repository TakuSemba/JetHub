package com.takusemba.jethub.di

import com.takusemba.jethub.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

  @ActivityScope
  @ContributesAndroidInjector(
    modules = [
      ActivityModule::class,
      FragmentBuilder::class
    ]
  )
  fun contributeMainActivity(): MainActivity
}