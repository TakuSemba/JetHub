package com.takusemba.jethub

import com.takusemba.jethub.di.DaggerAppComponent
import com.takusemba.jethub.di.DebugNetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DebugApp : App() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
      .application(this)
      .networkModule(DebugNetworkModule())
      .build()
  }
}