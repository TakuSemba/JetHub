package com.takusemba.jethub.di.screen

import com.takusemba.jethub.ui.fragment.FeedFragment
import com.takusemba.jethub.ui.fragment.RepositoriesFragment
import com.takusemba.jethub.ui.fragment.UsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainTabBuilder {

  @ContributesAndroidInjector
  fun contributeFeedFragment(): FeedFragment

  @ContributesAndroidInjector
  fun contributeRepositoriesFragment(): RepositoriesFragment

  @ContributesAndroidInjector
  fun contributeUsersFragment(): UsersFragment
}