package com.takusemba.jethub.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Module to provide [ViewModelProvider.Factory]
 */
@Module
interface ViewModelFactoryModule {

  @Binds fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
