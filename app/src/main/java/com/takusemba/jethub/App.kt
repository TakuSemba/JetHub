package com.takusemba.jethub

import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import com.takusemba.jethub.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class App : DaggerApplication() {

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    initAndroidThreeTen()
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().application(this).build()
  }

  /**
   * AndroidThreeTen Initialization
   */
  protected open fun initAndroidThreeTen() {
    AndroidThreeTen.init(this)
  }
}