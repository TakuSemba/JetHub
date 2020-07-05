package com.takusemba.jethub.pin

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import com.takusemba.jethub.base.ui.RepositoryItem
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repository
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class PinSection(
  lifecycleOwner: LifecycleOwner,
  private val userViewModel: UserViewModel,
  private val navigationViewModel: NavigationViewModel
) : Section() {

  init {
    userViewModel.pinedRepositories.observe(lifecycleOwner) { repositories ->
      updateResult(repositories)
    }
  }

  private fun updateResult(repositories: List<Repository>) {
    val items = mutableListOf<Item<*>>()
    (repositories).mapTo(items) { repository ->
      RepositoryItem(repository, userViewModel, navigationViewModel)
    }
    update(items)
  }
}