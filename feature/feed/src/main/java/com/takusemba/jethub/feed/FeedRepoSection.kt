package com.takusemba.jethub.feed

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import com.takusemba.jethub.model.Repository
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class FeedRepoSection(
  language: Language,
  lifecycleOwner: LifecycleOwner,
  feedViewModel: FeedViewModel,
  private val userViewModel: UserViewModel
) : Section() {

  init {
    feedViewModel.hotRepos(language).observe(lifecycleOwner) { repositories ->
      updateResult(repositories)
    }
  }

  private fun updateResult(repositories: List<Repository>) {
    val items = mutableListOf<Item<*>>()
    (repositories).mapTo(items) { repository -> RepositoryItem(repository, userViewModel) }
    update(items)
  }
}