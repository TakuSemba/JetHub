package com.takusemba.jethub.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.di.AccountModuleDependencies
import dagger.hilt.android.EntryPointAccessors

class AccountFragment : Fragment(R.layout.fragment_account) {

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

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

//    (view as ViewGroup).setContent {
//      Text("Hello World")
//    }
    startActivity(Intent(requireContext(), TestActivity::class.java))
  }
}