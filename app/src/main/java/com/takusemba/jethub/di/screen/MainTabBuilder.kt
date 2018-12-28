package com.takusemba.jethub.di.screen

import com.takusemba.jethub.ui.fragment.FeedFragment
import com.takusemba.jethub.ui.fragment.SearchRepositoriesFragment
import com.takusemba.jethub.ui.fragment.SearchUsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainTabBuilder {

  @ContributesAndroidInjector(modules = [FeedModule::class])
  fun contributeFeedFragment(): FeedFragment

  @ContributesAndroidInjector
  fun contributeRepositoriesFragment(): SearchRepositoriesFragment

  @ContributesAndroidInjector
  fun contributeUsersFragment(): SearchUsersFragment
}