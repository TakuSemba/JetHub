package com.takusemba.jethub.ui.item

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.takusemba.jethub.model.User
import com.takusemba.jethub.viewmodel.SearchUsersViewModel
import com.xwray.groupie.Section

class SearchUsersSection(
  lifecycleOwner: LifecycleOwner,
  searchUsersViewModel: SearchUsersViewModel
) : Section() {

  init {
    searchUsersViewModel.searchedUsers.observe(lifecycleOwner, Observer { repositories ->
      updateResult(repositories)
    })
  }

  private fun updateResult(users: List<User>) {
//    val items = mutableListOf<Item<*>>()
//    (repositories).mapTo(items) { repository -> RepositoryItem(repository) }
//    update(items)
  }
}