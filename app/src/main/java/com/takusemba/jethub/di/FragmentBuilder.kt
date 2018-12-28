package com.takusemba.jethub.di

import com.takusemba.jethub.di.screen.DeveloperDetailBuilder
import com.takusemba.jethub.di.screen.DeveloperDetailModule
import com.takusemba.jethub.di.screen.MainTabBuilder
import com.takusemba.jethub.ui.fragment.DeveloperDetailFragment
import com.takusemba.jethub.ui.fragment.MainTabFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

  @ContributesAndroidInjector(modules = [MainTabBuilder::class])
  fun contributeMainTabFragment(): MainTabFragment

  @ContributesAndroidInjector(modules = [DeveloperDetailModule::class, DeveloperDetailBuilder::class])
  fun contributeUserFragment(): DeveloperDetailFragment
}