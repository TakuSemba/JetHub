package com.takusemba.jethub.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.takusemba.jethub.R
import com.takusemba.jethub.extension.viewModelProvider
import com.takusemba.jethub.viewmodel.UserViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val userViewModel: UserViewModel by lazy {
    viewModelProvider(viewModelFactory) as UserViewModel
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    Log.d("MainActivity", "factory is ${System.identityHashCode(viewModelFactory)}")
    userViewModel.pin(1)
  }
}
