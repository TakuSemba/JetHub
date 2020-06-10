package com.takusemba.jethub

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application() {

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    initAndroidThreeTen()
  }

  /**
   * AndroidThreeTen Initialization
   */
  protected open fun initAndroidThreeTen() {
    AndroidThreeTen.init(this)
  }
}