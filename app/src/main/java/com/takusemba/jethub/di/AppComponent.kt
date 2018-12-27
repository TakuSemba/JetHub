package com.takusemba.jethub.di

import android.app.Application
import com.takusemba.jethub.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AndroidInjectionModule::class,
    NetworkModule::class
  ]
)
interface AppComponent : AndroidInjector<App> {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  override fun inject(app: App)
}
