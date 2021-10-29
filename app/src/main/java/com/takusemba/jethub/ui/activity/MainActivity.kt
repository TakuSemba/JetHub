package com.takusemba.jethub.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.takusemba.jethub.R
import com.takusemba.jethub.base.model.Direction.Developer
import com.takusemba.jethub.base.model.Direction.Pop
import com.takusemba.jethub.base.model.Direction.Repo
import com.takusemba.jethub.base.model.EventObserver
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.repo.RepoFragmentDirections
import com.takusemba.jethub.ui.fragment.MainTabFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

  private val navigationViewModel: NavigationViewModel by viewModels()
  private val systemViewModel: SystemViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launch {

      systemViewModel.isNightMode
        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
        .collect { isNightMode ->
          val nightModeSetting = if (isNightMode) {
            AppCompatDelegate.MODE_NIGHT_YES
          } else {
            AppCompatDelegate.MODE_NIGHT_NO
          }
          AppCompatDelegate.setDefaultNightMode(nightModeSetting)
        }

    }

    navigationViewModel.direction.observe(
      this,
      EventObserver { direction ->
        val navDirection = when (direction) {
          is Repo -> MainTabFragmentDirections.actionMainTabToRepo(direction.owner, direction.repo)
          is Developer -> RepoFragmentDirections.actionRepoToDeveloper(direction.name)
          is Pop -> {
            findNavController(R.id.host_fragment).popBackStack()
            return@EventObserver
          }
        }
        findNavController(R.id.host_fragment).navigate(navDirection)
      }
    )
  }
}
