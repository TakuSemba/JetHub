package com.takusemba.jethub.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.takusemba.jethub.base.model.Direction.Developer
import com.takusemba.jethub.base.model.Direction.Pop
import com.takusemba.jethub.base.model.Direction.Repo
import com.takusemba.jethub.base.ui.LocalActivity
import com.takusemba.jethub.base.ui.theme.JethubTheme
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val navigationViewModel: NavigationViewModel by viewModels()
  private val systemViewModel: SystemViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      CompositionLocalProvider(
        LocalActivity provides this
      ) {
        JethubTheme {
          MainContent()
        }
      }
    }

    lifecycleScope.launch {
      systemViewModel.uiState
        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
        .collect { uiState ->
          val nightModeSetting = if (uiState.isNightMode) {
            AppCompatDelegate.MODE_NIGHT_YES
          } else {
            AppCompatDelegate.MODE_NIGHT_NO
          }
          AppCompatDelegate.setDefaultNightMode(nightModeSetting)
        }
    }

    lifecycleScope.launch {
      navigationViewModel.direction
        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
        .collect { direction ->
          val navDirection = when (direction) {
            is Repo -> {
              // MainTabFragmentDirections.actionMainTabToRepo(direction.owner, direction.repo)
            }
            is Developer -> {
              // RepoFragmentDirections.actionRepoToDeveloper(direction.name)
            }
            is Pop -> {
              // findNavController(R.id.host_fragment).popBackStack()
              return@collect
            }
          }
          // findNavController(R.id.host_fragment).navigate(navDirection)
        }
    }
  }
}
