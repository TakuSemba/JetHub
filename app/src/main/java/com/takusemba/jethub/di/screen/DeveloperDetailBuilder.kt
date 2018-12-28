package com.takusemba.jethub.di.screen

import androidx.lifecycle.ViewModel
import com.takusemba.jethub.di.ViewModelKey
import com.takusemba.jethub.viewmodel.DeveloperDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DeveloperDetailBuilder {

  @Binds
  @IntoMap
  @ViewModelKey(DeveloperDetailViewModel::class)
  fun bindDeveloperDetailViewModel(viewModel: DeveloperDetailViewModel): ViewModel
}