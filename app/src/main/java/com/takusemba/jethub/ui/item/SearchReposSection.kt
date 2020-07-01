package com.takusemba.jethub.ui.item

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import com.takusemba.jethub.core.RepositoryItem
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.viewmodel.SearchReposViewModel
import com.takusemba.jethub.core.UserViewModel
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class SearchReposSection(
  lifecycleOwner: LifecycleOwner,
  searchReposViewModel: SearchReposViewModel,
  private val userViewModel: UserViewModel
) : Section() {

  init {
    searchReposViewModel.searchedRepos.observe(lifecycleOwner) { repositories ->
      updateResult(repositories)
    }
  }

  private fun updateResult(repositories: List<Repository>) {
    val items = mutableListOf<Item<*>>()
    (repositories).mapTo(items) { repository ->
      RepositoryItem(repository, userViewModel)
    }
    update(items)
  }
}