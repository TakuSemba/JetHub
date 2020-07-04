package com.takusemba.jethub.feed

import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.takusemba.jethub.base.ui.RepositoryItem
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repository
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class FeedRepoSection(
  fragment: Fragment,
  feedChannelViewModel: FeedChannelViewModel,
  private val userViewModel: UserViewModel
) : Section() {

  init {
    feedChannelViewModel.hotRepos.observe(fragment.viewLifecycleOwner) { repositories ->
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