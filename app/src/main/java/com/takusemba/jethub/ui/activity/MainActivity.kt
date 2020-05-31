package com.takusemba.jethub.ui.activity

import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.takusemba.jethub.R
import com.takusemba.jethub.viewmodel.UserViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(R.layout.activity_main) {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val userViewModel: UserViewModel by viewModels { viewModelFactory }
}
