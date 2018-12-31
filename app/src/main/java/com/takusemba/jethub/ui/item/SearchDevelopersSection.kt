package com.takusemba.jethub.ui.item

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.takusemba.jethub.model.SimpleDeveloper
import com.takusemba.jethub.viewmodel.SearchDevelopersViewModel
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class SearchDevelopersSection(
  lifecycleOwner: LifecycleOwner,
  searchDevelopersViewModel: SearchDevelopersViewModel
) : Section() {

  init {
    searchDevelopersViewModel.searchedDevelopers.observe(lifecycleOwner, Observer { repositories ->
      updateResult(repositories)
    })
  }

  private fun updateResult(developers: List<SimpleDeveloper>) {
    val items = mutableListOf<Item<*>>()
    (developers).mapTo(items) { user -> UserItem(user) }
    update(items)
  }
}