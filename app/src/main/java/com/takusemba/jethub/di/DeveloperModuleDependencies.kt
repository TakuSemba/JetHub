package com.takusemba.jethub.di

import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.repository.DeveloperRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface DeveloperModuleDependencies {

  fun provideDeveloperRepository(): DeveloperRepository

  fun provideErrorHandler(): ErrorHandler
}
