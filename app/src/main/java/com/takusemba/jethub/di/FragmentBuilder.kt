package com.takusemba.jethub.di

import com.takusemba.jethub.di.screen.MainTabBuilder
import com.takusemba.jethub.ui.fragment.MainTabFragment
import com.takusemba.jethub.ui.fragment.RepositoryFragment
import com.takusemba.jethub.ui.fragment.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

  @ContributesAndroidInjector(modules = [MainTabBuilder::class])
  fun contributeMainTabFragment(): MainTabFragment

  @ContributesAndroidInjector
  fun contributeRepositoryFragment(): RepositoryFragment

  @ContributesAndroidInjector
  fun contributeUserFragment(): UserFragment
}