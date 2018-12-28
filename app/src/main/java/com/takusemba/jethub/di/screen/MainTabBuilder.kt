package com.takusemba.jethub.di.screen

import com.takusemba.jethub.ui.fragment.FeedFragment
import com.takusemba.jethub.ui.fragment.SearchReposFragment
import com.takusemba.jethub.ui.fragment.SearchUsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainTabBuilder {

  @ContributesAndroidInjector(modules = [FeedModule::class])
  fun contributeFeedFragment(): FeedFragment

  @ContributesAndroidInjector
  fun contributeRepositoriesFragment(): SearchReposFragment

  @ContributesAndroidInjector
  fun contributeUsersFragment(): SearchUsersFragment
}