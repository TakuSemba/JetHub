package com.takusemba.jethub.di

import androidx.lifecycle.ViewModel
import com.takusemba.jethub.ui.activity.MainActivity
import com.takusemba.jethub.viewmodel.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface ActivityBuilder {

  @ActivityScope
  @ContributesAndroidInjector(
    modules = [
      ActivityModule::class,
      FragmentBuilder::class
    ]
  )
  fun contributeMainActivity(): MainActivity

  @Binds
  @IntoMap
  @ViewModelKey(UserViewModel::class)
  fun bindUserViewModel(userViewModel: UserViewModel): ViewModel
}