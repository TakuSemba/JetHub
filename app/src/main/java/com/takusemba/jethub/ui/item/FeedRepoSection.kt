package com.takusemba.jethub.ui.item

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.takusemba.jethub.model.Language
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.viewmodel.FeedViewModel
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class FeedRepoSection(
  language: Language,
  lifecycleOwner: LifecycleOwner,
  feedViewModel: FeedViewModel,
  private val userViewModel: UserViewModel
) : Section() {

  init {
    feedViewModel.hotRepos(language).observe(lifecycleOwner, Observer { repositories ->
      updateResult(repositories)
    })
  }

  private fun updateResult(repositories: List<Repository>) {
    val items = mutableListOf<Item<*>>()
    (repositories).mapTo(items) { repository -> RepositoryItem(repository, userViewModel) }
    update(items)
  }
}