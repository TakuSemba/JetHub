package com.takusemba.jethub.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.takusemba.jethub.R
import com.takusemba.jethub.base.model.Direction
import com.takusemba.jethub.base.model.EventObserver
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.repo.RepoFragmentDirections
import com.takusemba.jethub.ui.fragment.MainTabFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

  private val navigationViewModel: NavigationViewModel by viewModels()
  private val systemViewModel: SystemViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    systemViewModel.isNightMode.observe(this) { isNightMode ->
      val nightModeSetting = if (isNightMode) {
        AppCompatDelegate.MODE_NIGHT_YES
      } else {
        AppCompatDelegate.MODE_NIGHT_NO
      }
      AppCompatDelegate.setDefaultNightMode(nightModeSetting)
    }

    navigationViewModel.direction.observe(this, EventObserver { direction ->
      val navDirection = when (direction) {
        Direction.ACCOUNT -> MainTabFragmentDirections.actionMainTabToAccount()
        Direction.REPO -> MainTabFragmentDirections.actionMainTabToRepo()
        Direction.DEVELOPER -> RepoFragmentDirections.actionRepoToDeveloper()
      }
      findNavController(R.id.host_fragment).navigate(navDirection)
    })
  }
}