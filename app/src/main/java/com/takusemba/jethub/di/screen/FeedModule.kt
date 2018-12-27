package com.takusemba.jethub.di.screen

import androidx.lifecycle.ViewModel
import com.takusemba.jethub.di.ViewModelKey
import com.takusemba.jethub.viewmodel.FeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FeedModule {

  @Binds
  @IntoMap
  @ViewModelKey(FeedViewModel::class)
  fun bindUserViewModel(viewModel: FeedViewModel): ViewModel
}