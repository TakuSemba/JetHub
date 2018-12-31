package com.takusemba.jethub.di

import androidx.lifecycle.ViewModel
import com.takusemba.jethub.ui.activity.MainActivity
import com.takusemba.jethub.viewmodel.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Builder for activity.
 * [MainActivity] is an only activity of this app, since this is single-activity application.
 */
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
  fun bindUserViewModel(viewModel: UserViewModel): ViewModel
}