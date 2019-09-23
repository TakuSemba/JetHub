package com.takusemba.jethub.di.screen

import com.takusemba.jethub.di.screen.DeveloperDetailModule.Qualifiers.DEVELOPER_NAME
import com.takusemba.jethub.ui.fragment.DeveloperDetailFragment
import com.takusemba.jethub.ui.fragment.DeveloperDetailFragmentArgs
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Module for developer detail screen.
 */
@Module
class DeveloperDetailModule {

  object Qualifiers {
    const val DEVELOPER_NAME = "developer_name"
  }

  @Provides
  @Named(DEVELOPER_NAME)
  fun provideDeveloperName(fragment: DeveloperDetailFragment): String {
    return DeveloperDetailFragmentArgs.fromBundle(fragment.requireArguments()).developerName
  }
}