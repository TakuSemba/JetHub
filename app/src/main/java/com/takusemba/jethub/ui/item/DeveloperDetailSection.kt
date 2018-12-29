package com.takusemba.jethub.ui.item

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.viewmodel.DeveloperDetailViewModel
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class DeveloperDetailSection(
  lifecycleOwner: LifecycleOwner,
  developerDetailViewModel: DeveloperDetailViewModel,
  private val userViewModel: UserViewModel
) : Section() {

  init {
    developerDetailViewModel.developerRepos.observe(lifecycleOwner, Observer { repos ->
      updateResult(repos)
    })
  }

  private fun updateResult(repos: List<Repository>) {
    val items = mutableListOf<Item<*>>()
    (repos).mapTo(items) { repository -> RepositoryItem(repository, userViewModel) }
    update(items)
  }
}