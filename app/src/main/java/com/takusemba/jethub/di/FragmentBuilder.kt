package com.takusemba.jethub.di

import com.takusemba.jethub.di.screen.MainTabBuilder
import com.takusemba.jethub.ui.fragment.MainTabFragment
import com.takusemba.jethub.ui.fragment.RepoDetailFragment
import com.takusemba.jethub.ui.fragment.UserDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

  @ContributesAndroidInjector(modules = [MainTabBuilder::class])
  fun contributeMainTabFragment(): MainTabFragment

  @ContributesAndroidInjector
  fun contributeRepositoryFragment(): RepoDetailFragment

  @ContributesAndroidInjector
  fun contributeUserFragment(): UserDetailFragment
}