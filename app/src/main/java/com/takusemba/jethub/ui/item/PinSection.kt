package com.takusemba.jethub.ui.item

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class PinSection(
  lifecycleOwner: LifecycleOwner,
  private val userViewModel: UserViewModel
) : Section() {

  init {
    userViewModel.pinedRepositories.observe(lifecycleOwner) { repositories ->
      updateResult(repositories)
    }
  }

  private fun updateResult(repositories: List<Repository>) {
    val items = mutableListOf<Item<*>>()
    (repositories).mapTo(items) { repository -> RepositoryItem(repository, userViewModel) }
    update(items)
  }
}