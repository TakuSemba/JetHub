package com.takusemba.jethub

import com.takusemba.jethub.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Application class. [DaggerApplication] injects classes behind scene.
 */
open class App : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().application(this).build()
  }
}