package com.takusemba.jethub.feed

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.takusemba.jethub.base.ui.RepositoryItem
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repository
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class FeedRepoSection(
  fragment: Fragment,
  language: String
) : Section() {

  private val feedViewModel: FeedViewModel by fragment.viewModels(
    ownerProducer = { fragment.requireParentFragment().requireParentFragment() }
  )
  private val userViewModel: UserViewModel by fragment.activityViewModels()
  private val navigationViewModel: NavigationViewModel by fragment.activityViewModels()

  init {
    feedViewModel.hotRepos(language).observe(fragment.viewLifecycleOwner) { repositories ->
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