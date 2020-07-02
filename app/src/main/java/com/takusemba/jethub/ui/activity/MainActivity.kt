package com.takusemba.jethub.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.takusemba.jethub.R
import com.takusemba.jethub.base.Direction
import com.takusemba.jethub.base.NavigationViewModel
import com.takusemba.jethub.ui.fragment.MainTabFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

  private val navigationViewModel: NavigationViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    navigationViewModel.direction.observe(this) { direction ->
      val navDirection = when (direction) {
        Direction.ACCOUNT -> MainTabFragmentDirections.actionMainTabToAccount()
      }
      findNavController(R.id.host_fragment).navigate(navDirection)
    }
  }
}