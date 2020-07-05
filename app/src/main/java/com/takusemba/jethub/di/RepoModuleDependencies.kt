package com.takusemba.jethub.di

import com.takusemba.jethub.api.RepoApi
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface RepoModuleDependencies {

  fun provideRepoApi(): RepoApi
}