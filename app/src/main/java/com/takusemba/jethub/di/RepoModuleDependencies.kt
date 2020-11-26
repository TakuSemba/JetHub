package com.takusemba.jethub.di

import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.repository.RepoRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface RepoModuleDependencies {

  fun provideRepoRepository(): RepoRepository

  fun provideErrorHandler(): ErrorHandler
}
