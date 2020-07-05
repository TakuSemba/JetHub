package com.takusemba.jethub.di

import com.takusemba.jethub.repository.RepoRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface RepoModuleDependencies {

  fun provideRepoRepository(): RepoRepository
}