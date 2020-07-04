package com.takusemba.jethub.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.di.AccountModuleDependencies
import dagger.hilt.android.EntryPointAccessors

class AccountFragment : Fragment() {

  private val userViewModel: UserViewModel by activityViewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerAccountComponent.builder()
      .context(requireContext())
      .appDependencies(
        EntryPointAccessors.fromApplication(
          requireContext().applicationContext,
          AccountModuleDependencies::class.java
        )
      )
      .build()
      .inject(this)

    userViewModel.developer.observe(this) { developer ->
      Log.d("TEST", "developer: $developer")
    }
  }
}