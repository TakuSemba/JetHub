package com.takusemba.jethub.feed

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.takusemba.jethub.repository.RepoRepository
import javax.inject.Inject

/**
 * Currently, Hilts does not support to inject runtime arguments into ViewModel.
 * Until they support it, custom [ViewModelProvider.Factory] would be an option.
 * https://github.com/google/dagger/issues/1825
 */
class FeedChannelViewModelProviderFactory @Inject constructor(
  private val fragment: Fragment,
  private val repoRepository: RepoRepository
) : ViewModelProvider.Factory {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    val language = fragment.requireArguments().getString(FeedChannelFragment.KEY_LANGUAGE)
    return FeedChannelViewModel(requireNotNull(language), repoRepository) as T
  }
}