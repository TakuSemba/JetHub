package com.takusemba.jethub.di.screen

import androidx.lifecycle.ViewModel
import com.takusemba.jethub.di.ViewModelKey
import com.takusemba.jethub.ui.fragment.FeedFragment
import com.takusemba.jethub.ui.fragment.SearchReposFragment
import com.takusemba.jethub.ui.fragment.SearchUsersFragment
import com.takusemba.jethub.viewmodel.FeedViewModel
import com.takusemba.jethub.viewmodel.SearchReposViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface MainTabBuilder {

  @ContributesAndroidInjector
  fun contributeFeedFragment(): FeedFragment

  @ContributesAndroidInjector
  fun contributeRepositoriesFragment(): SearchReposFragment

  @ContributesAndroidInjector
  fun contributeUsersFragment(): SearchUsersFragment

  @Binds
  @IntoMap
  @ViewModelKey(FeedViewModel::class)
  fun bindUserViewModel(viewModel: FeedViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(SearchReposViewModel::class)
  fun bindSearchReposViewModel(viewModel: SearchReposViewModel): ViewModel
}