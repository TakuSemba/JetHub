package com.takusemba.jethub.account

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.takusemba.jethub.di.AccountModuleDependencies
import com.takusemba.jethub.repository.DeveloperRepository
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class AccountActivity : AppCompatActivity() {

  @Inject lateinit var developerRepository: DeveloperRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerAccountComponent.builder()
      .context(this)
      .appDependencies(
        EntryPointAccessors.fromApplication(
          applicationContext,
          AccountModuleDependencies::class.java
        )
      )
      .build()
      .inject(this)

    Log.d("TEST", "developerRepository is not null? ${(developerRepository != null)}")
  }
}