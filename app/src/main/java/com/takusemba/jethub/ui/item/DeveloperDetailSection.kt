package com.takusemba.jethub.ui.item

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.takusemba.jethub.model.Developer
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.viewmodel.DeveloperDetailViewModel
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class DeveloperDetailSection(
  lifecycleOwner: LifecycleOwner,
  searchReposViewModel: DeveloperDetailViewModel
) : Section() {

  init {
    searchReposViewModel.developer.observe(lifecycleOwner, Observer { developer ->
      updateResult(developer, searchReposViewModel.developerRepos.value ?: emptyList())
    })

    searchReposViewModel.developerRepos.observe(lifecycleOwner, Observer { repos ->
      val developer = searchReposViewModel.developer.value ?: return@Observer
      updateResult(developer, repos)
    })
  }

  private fun updateResult(developer: Developer, repos: List<Repository>) {
    setHeader(DeveloperDetailHeaderItem(developer))
    val items = mutableListOf<Item<*>>()
    (repos).mapTo(items) { repository -> RepositoryItem(repository) }
    update(items)
  }
}